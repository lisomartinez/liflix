package cloud.liso.liflix.services.torrent.implementations.zooqle.selectors.element;

import cloud.liso.liflix.services.http_client.DOMDocument;
import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.http_client.DocumentSelector;
import cloud.liso.liflix.services.http_client.JsoupDOMElement;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZooqleDocumentSelector implements DocumentSelector {
    private static final String CRITERIA = "table > tbody > tr";

    @Override
    public List<DOMElement> apply(DOMDocument document) {
        return ((Document) document.content())
                .select(CRITERIA)
                .stream()
                .map(JsoupDOMElement::new)
                .collect(Collectors.toList());
    }
}
