package cloud.liso.liflix.services.torrent.zooqle.selectors.element;

import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.ElementSelector;
import org.jsoup.nodes.Element;

public class ZooqleTorrentElementSelector implements ElementSelector {
    private static final String CRITERIA = "table > tbody > tr";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
