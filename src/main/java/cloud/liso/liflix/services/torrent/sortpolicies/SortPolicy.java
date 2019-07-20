package cloud.liso.liflix.services.torrent.sortpolicies;

import cloud.liso.liflix.model.torrent.Torrent;

import java.util.List;

public interface SortPolicy {
    List<Torrent> sort(List<Torrent> torrents);
}
