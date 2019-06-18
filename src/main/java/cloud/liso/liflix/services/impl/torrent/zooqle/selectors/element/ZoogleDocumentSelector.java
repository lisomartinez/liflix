package cloud.liso.liflix.services.impl.torrent.zooqle.selectors.element;

import cloud.liso.liflix.services.api.httpClient.DOMDocument;
import cloud.liso.liflix.services.api.httpClient.DOMElement;
import cloud.liso.liflix.services.api.httpClient.DocumentSelector;
import cloud.liso.liflix.services.impl.torrent.zooqle.ZooqleDOMElement;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZoogleDocumentSelector implements DocumentSelector {
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
