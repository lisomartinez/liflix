package cloud.liso.liflix.services.torrent.zooqle.selectors.element;

import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.ElementSelector;
import org.jsoup.nodes.Element;

public class ZooqleMagnetLinkElementSelector implements ElementSelector {
    private static final String CRITERIA = "td:nth-child(3)  li:nth-child(2) a";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).attr("href");
    }
}
