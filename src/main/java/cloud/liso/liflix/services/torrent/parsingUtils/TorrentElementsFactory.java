package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.services.httpClient.DOMElement;

public interface TorrentElementsFactory {
    TorrentElements createTorrentElements(DOMElement element);
}
