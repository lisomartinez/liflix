package cloud.liso.liflix.services.http_client;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Data
public class JsoupDOMDocument implements DOMDocument {
    private Document content;

    public JsoupDOMDocument(WebPage content) {
        this.content = Jsoup.parse(content.getContent());
    }

    @Override
    public Document content() {
        return content;
    }

}
