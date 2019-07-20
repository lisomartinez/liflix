package cloud.liso.liflix.services.imdb;

import cloud.liso.liflix.services.http_client.WebPage;
import cloud.liso.liflix.services.http_client.WebPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ImdbWebPageParser implements WebPageParser {

    @Override
    public String parseWebpage(WebPage webpage) {
        Document document = Jsoup.parse(webpage.getContent());
        return document.select(".video-modal").attr("data-video");
    }
}
