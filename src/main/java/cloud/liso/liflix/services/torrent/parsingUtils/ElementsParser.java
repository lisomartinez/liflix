package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.services.httpClient.DOMElement;

public interface ElementsParser {
    TorrentElements parse(DOMElement element, Selectors selectors);
}
