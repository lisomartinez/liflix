package cloud.liso.liflix.services.httpClient;

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
