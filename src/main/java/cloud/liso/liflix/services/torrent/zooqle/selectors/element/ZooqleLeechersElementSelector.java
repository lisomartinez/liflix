package cloud.liso.liflix.services.torrent.zooqle.selectors.element;

import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.ElementSelector;
import org.jsoup.nodes.Element;

public class ZooqleLeechersElementSelector implements ElementSelector {
    private static final String CRITERIA = "td:nth-child(6) div:nth-child(2)";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
