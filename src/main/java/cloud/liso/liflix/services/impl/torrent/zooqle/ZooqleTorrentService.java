package cloud.liso.liflix.services.impl.torrent.zooqle;

import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;
import cloud.liso.liflix.repositories.TorrentListRepository;
import cloud.liso.liflix.services.api.torrent.SortPolicy;
import cloud.liso.liflix.services.api.torrent.TorrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ZooqleTorrentService implements TorrentService {

    private static final int TTL = 1;

    private TorrentListRepository torrentListRepository;

    private TorrentAssociatedEntitiesRepositoryFacade associatedEntitiesServices;

    private ZooqleServiceFacade zooqleServiceFacade;

    @Autowired
    public ZooqleTorrentService(TorrentListRepository torrentListRepository,
                                TorrentAssociatedEntitiesRepositoryFacade associatedEntitiesServices,
                                ZooqleServiceFacade zooqleServiceFacade) {

        this.torrentListRepository = torrentListRepository;
        this.associatedEntitiesServices = associatedEntitiesServices;
        this.zooqleServiceFacade = zooqleServiceFacade;
    }

    @Override
    @Cacheable(value = "torrents")
    public List<Torrent> getTorrents(Request request, SortPolicy sortPolicy) {
        Optional<TorrentList> list = torrentListRepository.findByShowIdAndSeasonNumberAndEpisodeNumber(
                request.getShowId(),
                request.getSeasonNumber(),
                request.getEpisodeNumber());

        if (list.isPresent() && lastUpdateTimeIsLessThanTTL(list.get())) {
            return sortPolicy.sort(list.get().getTorrents());
        } else {
            List<Torrent> fetchedTorrents = zooqleServiceFacade.torrentsFrom(request);
            List<Torrent> withSavedComponents = associatedEntitiesServices
                    .getOrPersistTorrentAssociatedEntitiesOfAll(fetchedTorrents);
            torrentListRepository.save(torrentListFrom(request, withSavedComponents));
            return sortPolicy.sort(withSavedComponents);
        }
    }


    private boolean lastUpdateTimeIsLessThanTTL(TorrentList torrentList) {
        return Duration.between(torrentList.getLastUpdate(), LocalDateTime.now().withSecond(0).withNano(0)).toHours() < TTL;
    }

    private TorrentList torrentListFrom(Request req, List<Torrent> torrents) {
        TorrentList torrentList = createTorrentList(req, torrents);
        linkTorrentsWithTorrentList(torrents, torrentList);
        return torrentList;
    }

    private void linkTorrentsWithTorrentList(List<Torrent> torrents, TorrentList torrentList) {
        torrents.forEach(t -> t.setTorrentList(torrentList));
    }

    private TorrentList createTorrentList(Request req, List<Torrent> torrents) {
        return TorrentList.builder()
                .showId(req.getShowId())
                .seasonNumber(req.getSeasonNumber())
                .episodeNumber(req.getEpisodeNumber())
                .torrents(torrents)
                .lastUpdate(LocalDateTime.now().withNano(0))
                .build();
    }

    @Override
    public Torrent getTorrent(Request request, SortPolicy searchCriteria) {
        List<Torrent> torrents = getTorrents(request, searchCriteria);
        return torrents.isEmpty() ? Torrent.empty() : torrents.get(0);
    }
}
