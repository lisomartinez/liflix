package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.services.httpClient.ElementSelector;

import java.util.Map;

public interface Selectors {
    Map<SelectorType, ElementSelector> getSelectorsByType();
}
