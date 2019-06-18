package cloud.liso.liflix.services.impl.torrent.zooqle;

import cloud.liso.liflix.services.api.httpClient.DOMElement;
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
