package cloud.liso.liflix.services.torrent;

import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.torrent.sortPolicies.SortPolicy;

import java.util.List;

public interface TorrentService {
    List<Torrent> getTorrents(Request request, SortPolicy searchCriteria);

    Torrent getTorrent(Request request, SortPolicy searchCriteria);
}
