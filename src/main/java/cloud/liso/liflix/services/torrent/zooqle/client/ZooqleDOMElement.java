package cloud.liso.liflix.services.torrent.zooqle.client;

import cloud.liso.liflix.services.httpClient.DOMElement;
import lombok.Data;
import org.jsoup.nodes.Element;

@Data
public class ZooqleDOMElement implements DOMElement {

    private Element element;

    public ZooqleDOMElement(Element element) {
        this.element = element;
    }

    public Element content() {
        return element;
    }

}
