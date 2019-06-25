package cloud.liso.liflix.services.torrent.searchEngine.criteria;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;
import cloud.liso.liflix.services.torrent.SortPolicy;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

public class BestResolutionMostSeedersSortPolicy implements SortPolicy {
    @Override
    public Optional<Torrent> apply(TorrentList info) {
        List<Torrent> torrents;
        if (info == null) {
            return Optional.empty();
        } else {
            torrents = info.getTorrents();
            sort(info.getTorrents());
            if (torrents.size() >= 1) {
                return Optional.of(torrents.get(0));
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Torrent> sort(List<Torrent> torrents) {
        torrents.sort(comparing(Torrent::getResolution).reversed()
                .thenComparing(comparing(Torrent::getSeeders).reversed()));
        return torrents;
    }


}
