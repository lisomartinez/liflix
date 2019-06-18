package cloud.liso.liflix.services.impl.torrent.zooqle.selectors.element;

import cloud.liso.liflix.services.api.httpClient.DOMElement;
import cloud.liso.liflix.services.api.httpClient.ElementSelector;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ZoogleSizeElementSelector implements ElementSelector {
    private static final String CRITERIA = "td:nth-child(4) .progress-bar";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).text();
    }
}
