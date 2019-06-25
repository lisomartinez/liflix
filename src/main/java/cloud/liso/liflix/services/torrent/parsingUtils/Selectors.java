package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.services.httpClient.ElementSelector;

import java.util.Map;

public interface Selectors extends StringSelectors, ClassSelectors {
    Map<SelectorType, ElementSelector> getSelectorsByType();
}
