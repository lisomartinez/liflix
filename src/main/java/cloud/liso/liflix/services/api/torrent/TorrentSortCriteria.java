package cloud.liso.liflix.services.api.torrent;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;

import java.util.List;
import java.util.Optional;

public interface TorrentSortCriteria {
    Optional<Torrent> apply(TorrentList info);

    List<Torrent> sort(List<Torrent> torrents);
}
