package cloud.liso.liflix.services.torrent.implementations.zooqle.selectors.element;

import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.ElementSelector;
import org.jsoup.nodes.Element;

public class ZooqleSizeElementSelector implements ElementSelector {
    private static final String CRITERIA = "td:nth-child(4) .progress-bar";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
