package cloud.liso.liflix.services.torrent.zooqle.selectors.element;

import cloud.liso.liflix.services.httpClient.DOMDocument;
import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.DocumentSelector;
import cloud.liso.liflix.services.torrent.zooqle.client.ZooqleDOMElement;
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
                .map(ZooqleDOMElement::new)
                .collect(Collectors.toList());
    }
}
