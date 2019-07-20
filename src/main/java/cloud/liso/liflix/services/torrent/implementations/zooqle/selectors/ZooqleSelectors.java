package cloud.liso.liflix.services.torrent.implementations.zooqle.selectors;

import cloud.liso.liflix.services.http_client.ElementSelector;
import cloud.liso.liflix.services.torrent.implementations.zooqle.selectors.element.*;
import cloud.liso.liflix.services.torrent.parsing.SelectorType;
import cloud.liso.liflix.services.torrent.parsing.Selectors;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ZooqleSelectors implements Selectors {
    private Map<SelectorType, ElementSelector> selectors;

    public ZooqleSelectors() {
        this.selectors = new EnumMap<>(SelectorType.class);
        this.selectors.put(SelectorType.TITLE, new ZooqleTitleElementSelector());
        this.selectors.put(SelectorType.MAGNET, new ZooqleMagnetLinkElementSelector());
        this.selectors.put(SelectorType.SEEDERS, new ZooqleSeederElementSelector());
        this.selectors.put(SelectorType.LEECHERS, new ZooqleLeechersElementSelector());
        this.selectors.put(SelectorType.SIZE, new ZooqleSizeElementSelector());
    }

    @Override
    public Map<SelectorType, ElementSelector> getSelectorsByType() {
        return selectors;
    }
}
