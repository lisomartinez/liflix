package cloud.liso.liflix.services.torrent.sortPolicies.policies;

import cloud.liso.liflix.model.torrent.Resolution;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;
import cloud.liso.liflix.services.torrent.sortPolicies.SortPolicy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class HDReadyMostSeedersSortPolicy implements SortPolicy {
    @Override
    public Optional<Torrent> apply(TorrentList info) {
        List<Torrent> torrents;
        if (info == null) {
            return Optional.empty();
        } else {
            torrents = info.getTorrents();
            sort(torrents);
            if (torrents.size() >= 1) {
                return Optional.of(torrents.get(0));
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Torrent> sort(List<Torrent> torrents) {
        return torrents.stream()
                .filter(t -> t.getResolution().equals(Resolution.RESOLUTION_720P))
                .sorted(comparing(Torrent::getSeeders).reversed())
                .collect(Collectors.toList());
    }
}
