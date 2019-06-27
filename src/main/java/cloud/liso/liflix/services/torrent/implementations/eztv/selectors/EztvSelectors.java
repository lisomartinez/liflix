package cloud.liso.liflix.services.torrent.implementations.eztv.selectors;

import cloud.liso.liflix.services.httpClient.ElementSelector;
import cloud.liso.liflix.services.torrent.parsing.SelectorType;
import cloud.liso.liflix.services.torrent.parsing.Selectors;

import java.util.HashMap;
import java.util.Map;

public class EztvSelectors implements Selectors {
    private Map<SelectorType, ElementSelector> selectors;

    public EztvSelectors() {
        selectors = new HashMap<>();
        selectors.put(SelectorType.TITLE, new EztvTitleSelector());
        selectors.put(SelectorType.MAGNET, new EztvMagnetSelector());
        selectors.put(SelectorType.SEEDERS, new EztvSeedersSelector());
        selectors.put(SelectorType.LEECHERS, new EztvLeecherssSelector());
        selectors.put(SelectorType.SIZE, new EztvSizeSelector());
    }

    @Override
    public Map<SelectorType, ElementSelector> getSelectorsByType() {
        return selectors;
    }
}
