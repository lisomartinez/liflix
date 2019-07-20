package cloud.liso.liflix.services.torrent.implementations.zooqle.selectors.element;

import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.http_client.ElementSelector;
import org.jsoup.nodes.Element;

public class ZooqleTitleElementSelector implements ElementSelector {
    private static final String CRITERIA = "td:nth-child(2) a";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
