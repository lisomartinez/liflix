package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMElement;

public interface TorrentElementsFactory {
    Torrent.TorrentBuilder createTorrentElements(DOMElement element, Torrent.TorrentBuilder builder);
}
