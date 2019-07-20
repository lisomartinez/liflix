package cloud.liso.liflix.services.torrent.implementations.zooqle.selectors.element;

import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.http_client.ElementSelector;
import org.jsoup.nodes.Element;

public class ZooqleTorrentElementSelector implements ElementSelector {
    private static final String CRITERIA = "table > tbody > tr";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
