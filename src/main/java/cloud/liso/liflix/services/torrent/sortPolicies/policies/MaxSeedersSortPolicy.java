package cloud.liso.liflix.services.torrent.sortPolicies.policies;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;
import cloud.liso.liflix.services.torrent.sortPolicies.SortPolicy;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

public class MaxSeedersSortPolicy implements SortPolicy {
    @Override
    public Optional<Torrent> apply(TorrentList info) {
        List<Torrent> torrents;
        if (info == null) {
            return Optional.empty();
        } else {
            torrents = info.getTorrents();
            if (torrents.size() >= 1) {
                return Optional.of(torrents.get(0));
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Torrent> sort(List<Torrent> torrents) {
        torrents.sort(comparing(Torrent::getSeeders).reversed()
                .thenComparing(comparing(Torrent::getResolution).reversed()));
        return torrents;
    }
}
