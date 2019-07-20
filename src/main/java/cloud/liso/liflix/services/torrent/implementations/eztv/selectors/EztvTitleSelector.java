package cloud.liso.liflix.services.torrent.implementations.eztv.selectors;

import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.http_client.ElementSelector;
import org.jsoup.nodes.Element;

public class EztvTitleSelector implements ElementSelector {
    private static final String CRITERIA = "td:nth-child(2)";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
