package cloud.liso.liflix.services.torrent.sortpolicies.policies;

import cloud.liso.liflix.model.torrent.Resolution;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicy;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class HDReadyMostSeedersSortPolicy implements SortPolicy {
    @Override
    public List<Torrent> sort(List<Torrent> torrents) {
        return torrents.stream()
                .filter(t -> t.getResolution().equals(Resolution.RESOLUTION_OF_720P))
                .sorted(comparing(Torrent::getSeeders).reversed())
                .collect(Collectors.toList());
    }
}
