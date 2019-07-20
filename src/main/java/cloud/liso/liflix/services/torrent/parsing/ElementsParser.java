package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMElement;

public interface ElementsParser {
    Torrent.TorrentBuilder parse(DOMElement element, Selectors selectors, Torrent.TorrentBuilder builder);
}
