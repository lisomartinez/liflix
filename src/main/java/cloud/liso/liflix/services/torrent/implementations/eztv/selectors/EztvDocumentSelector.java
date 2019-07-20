package cloud.liso.liflix.services.torrent.implementations.eztv.selectors;

import cloud.liso.liflix.services.http_client.DOMDocument;
import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.http_client.DocumentSelector;
import cloud.liso.liflix.services.http_client.JsoupDOMElement;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

public class EztvDocumentSelector implements DocumentSelector {
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
