package cloud.liso.liflix.services.torrent.implementations.eztv.selectors;

import cloud.liso.liflix.services.httpClient.DOMElement;
import org.jsoup.nodes.Element;

public class EztvMagnetSelector implements cloud.liso.liflix.services.httpClient.ElementSelector {
    private static final String CRITERIA = "td:nth-child(3)";

    @Override
    public String apply(DOMElement element) {
        return ((Element) element.content()).select(CRITERIA).select("a[href]").attr("href");
    }
}
