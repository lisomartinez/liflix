package cloud.liso.liflix.services.impl.torrent.zooqle.selectors;

import cloud.liso.liflix.services.api.httpClient.ElementSelector;
import cloud.liso.liflix.services.api.httpClient.Selector;
import cloud.liso.liflix.services.api.httpClient.Selectors;
import cloud.liso.liflix.services.impl.torrent.zooqle.selectors.element.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ZooqleSelectors implements Selectors {
    private Map<Selector, ElementSelector> selectors;

    public ZooqleSelectors() {
        this.selectors = new HashMap<>();
        this.selectors.put(Selector.TITLE, new ZooqleTitleElementSelector());
        this.selectors.put(Selector.MAGNET, new ZooqleMagnetLinkElementSelector());
        this.selectors.put(Selector.SIZE, new ZooqleSizeElementSelector());
        this.selectors.put(Selector.SEEDERS, new ZooqleSeederElementSelector());
        this.selectors.put(Selector.LEECHERS, new ZooqleLeechersElementSelector());
    }


    @Override
    public ElementSelector get(Selector selector) {
        return this.selectors.get(selector);
    }
}
