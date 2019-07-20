package cloud.liso.liflix.services.torrent.implementations.eztv.selectors;

import cloud.liso.liflix.services.http_client.DOMElement;
import org.jsoup.nodes.Element;

public class EztvSizeSelector implements cloud.liso.liflix.services.http_client.ElementSelector {
    private static final String CRITERIA = "td:nth-child(4)";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
