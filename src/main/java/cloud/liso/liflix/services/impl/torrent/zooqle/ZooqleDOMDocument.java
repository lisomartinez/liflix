package cloud.liso.liflix.services.impl.torrent.zooqle;

import cloud.liso.liflix.services.api.httpClient.DOMDocument;
import cloud.liso.liflix.services.api.httpClient.WebPage;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Data
public class ZooqleDOMDocument implements DOMDocument {
    private Document content;

    public ZooqleDOMDocument(WebPage content) {
        this.content = Jsoup.parse(content.getContent());
    }

    @Override
    public Document content() {
        return content;
    }

}
