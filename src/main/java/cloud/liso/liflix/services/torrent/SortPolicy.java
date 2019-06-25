package cloud.liso.liflix.services.torrent;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;

import java.util.List;
import java.util.Optional;

public interface SortPolicy {
    Optional<Torrent> apply(TorrentList info);

    List<Torrent> sort(List<Torrent> torrents);
}
