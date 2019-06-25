package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.services.httpClient.ElementSelector;

public interface StringSelectors {
    ElementSelector getTitle();

    ElementSelector getMagnet();

    ElementSelector getSeeders();

    ElementSelector getLeechers();
}
