package cloud.liso.liflix.services.torrent.implementations.eztv.selectors;

import cloud.liso.liflix.services.httpClient.DOMDocument;
import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.DocumentSelector;
import cloud.liso.liflix.services.httpClient.JsoupDOMElement;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

public class EztvDocumentSelector implements DocumentSelector {
    private static final String CRITERIA = "table > tbody > tr";

    @Override
    public List<DOMElement> apply(DOMDocument document) {
        return ((Document) document.content()).getElementsByTag("table")
                .last()
                .getElementsByAttributeValue("name", "hover")
                .stream()
                .map(JsoupDOMElement::new)
                .collect(Collectors.toList());
    }
}
