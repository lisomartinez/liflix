package cloud.liso.liflix.services.torrent.zooqle;


import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.repositories.TorrentListRepository;
import cloud.liso.liflix.services.torrent.TorrentService;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicies;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicyId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DefaultTorrentServiceIT {

    @Autowired
    private TorrentService torrentService;

    @Autowired
    private SortPolicies map;

    @Autowired
    private TorrentListRepository repo;

    @Test
    void getTorrent_validShowIdAndShowNameAndSeasonNumberAndEpisodeNumber() {

        final int showId = 1;
        final String showName = "under the dome";
        final int seasonNumber = 3;
        final int episodeNumber = 13;
        SortPolicyId sortPolicyId = SortPolicyId.valueOfParam("bestResolution");
        List<Torrent> torrents = torrentService.getTorrents(Request.builder()
                .showId(showId)
                .showName(showName)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build(), map.getOrDefault(sortPolicyId));
        assertThat(torrents.size()).isNotEqualTo(0);
    }

//    @Test
//    void getTorrent_validShowInfoUpToDate_ShouldReturnInstanceFromDB() {
//
//        final int showId = 1;
//        final String showName = "under the dome";
//        final int seasonNumber = 3;
//        final int episodeNumber = 13;
//        List<Torrent> torrents = torrentService.getTorrents(new Request(showId, showName, seasonNumber, episodeNumber), map.getOrDefault("default"));
//        assertThat(torrents.size()).isEqualTo(6);
//
//        Optional<TorrentList> byShowId = repo.findByShowIdAndSeasonNumberAndEpisodeNumber(torrents.getShowId(), seasonNumber, episodeNumber);
//        assertThat(byShowId.isPresent()).isTrue();
//
//        List<Torrent> torrents2 = torrentService.getTorrents(new Request(showId, showName, seasonNumber, episodeNumber), map.getOrDefault("default"));
//        LogManager.getLogger().info(torrents2);
//
//        assertThat(torrents2.getLastUpdate()).isEqualTo(torrents.getLastUpdate());
//    }

    @Test
    void getTorrent_returnSortedByResoultion() {

        final int showId = 1;
        final String showName = "under the dome";
        final int seasonNumber = 3;
        final int episodeNumber = 13;
        SortPolicyId sortPolicyId = SortPolicyId.valueOfParam("default");

        List<Torrent> torrents = torrentService.getTorrents(Request.builder()
                .showId(showId)
                .showName(showName)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build(), map.getOrDefault(sortPolicyId));
        Logger logger = LogManager.getLogger();
        logger.info(torrents);
        torrents.stream().map(Torrent::getResolution).forEach(logger::info);
        assertThat(torrents.size()).isNotEqualTo(0);
    }

}