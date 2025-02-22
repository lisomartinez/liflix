package cloud.liso.liflix.services.http_client;

import lombok.Data;
import org.jsoup.nodes.Element;

@Data
public class JsoupDOMElement implements DOMElement {

    private Element element;

    public JsoupDOMElement(Element element) {
        this.element = element;
    }

    public Element content() {
        return element;
    }

}
