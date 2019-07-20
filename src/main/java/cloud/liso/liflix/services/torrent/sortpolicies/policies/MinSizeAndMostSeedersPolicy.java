package cloud.liso.liflix.services.torrent.sortpolicies.policies;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicy;

import java.util.List;

import static java.util.Comparator.comparing;

public class MinSizeAndMostSeedersPolicy implements SortPolicy {
    @Override
    public List<Torrent> sort(List<Torrent> torrents) {
        torrents.sort(comparing(Torrent::getSize)
                .thenComparing(comparing(Torrent::getSeeders).reversed()));
        return torrents;
    }

}
