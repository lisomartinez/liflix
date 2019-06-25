package cloud.liso.liflix.services.torrent.zooqle.client;

import cloud.liso.liflix.services.httpClient.DOMDocument;
import cloud.liso.liflix.services.httpClient.WebPage;
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
