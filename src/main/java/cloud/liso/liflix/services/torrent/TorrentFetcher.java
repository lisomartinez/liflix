package cloud.liso.liflix.services.torrent;

import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.model.torrent.Torrent;

import java.util.List;

public interface TorrentFetcher {
    List<Torrent> torrentsFrom(Request request);
}
