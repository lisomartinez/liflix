package cloud.liso.liflix.services.torrent.zooqle.selectors;

import cloud.liso.liflix.services.httpClient.ElementSelector;
import cloud.liso.liflix.services.torrent.parsingUtils.SelectorType;
import cloud.liso.liflix.services.torrent.parsingUtils.Selectors;
import cloud.liso.liflix.services.torrent.zooqle.selectors.element.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ZooqleSelectors implements Selectors {
    private Map<SelectorType, ElementSelector> selectors;

    public ZooqleSelectors() {
        this.selectors = new HashMap<>();
        this.selectors.put(SelectorType.TITLE, new ZooqleTitleElementSelector());
        this.selectors.put(SelectorType.MAGNET, new ZooqleMagnetLinkElementSelector());
        this.selectors.put(SelectorType.SEEDERS, new ZooqleSeederElementSelector());
        this.selectors.put(SelectorType.LEECHERS, new ZooqleLeechersElementSelector());
        this.selectors.put(SelectorType.SIZE, new ZooqleSizeElementSelector());
    }

    @Override
    public ElementSelector getTitle() {
        return selectors.get(SelectorType.TITLE);
    }

    @Override
    public ElementSelector getMagnet() {
        return selectors.get(SelectorType.MAGNET);
    }

    @Override
    public ElementSelector getSeeders() {
        return selectors.get(SelectorType.SEEDERS);
    }

    @Override
    public ElementSelector getLeechers() {
        return selectors.get(SelectorType.LEECHERS);
    }

    @Override
    public ElementSelector getSize() {
        return selectors.get(SelectorType.SIZE);
    }

    @Override
    public Map<SelectorType, ElementSelector> getSelectorsByType() {
        return selectors;
    }
}
