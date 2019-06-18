package cloud.liso.liflix.services.impl.torrent.zooqle;

import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.api.httpClient.URLConstructor;
import cloud.liso.liflix.services.api.httpClient.WebClient;
import cloud.liso.liflix.services.api.httpClient.WebPage;
import cloud.liso.liflix.services.api.torrent.EpisodeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZooqleServiceFacade {

    private URLConstructor urlConstructor;

    private WebClient client;

    private EpisodeParser parser;

    @Autowired
    public ZooqleServiceFacade(@Qualifier("zooqleHttpURLConstructor") URLConstructor urlConstructor,
                               @Qualifier("zooqleWebClient") WebClient client,
                               @Qualifier("zooqleEpisodeParser") EpisodeParser parser) {
        this.urlConstructor = urlConstructor;
        this.client = client;
        this.parser = parser;
    }

    public List<Torrent> torrentsFrom(Request request) {
        String url = urlConstructor.getTorrentsPageURL(request.getShowName(),
                request.getSeasonNumber(),
                request.getEpisodeNumber());
        WebPage webPage = client.fetchWebPage(url);
        List<Torrent> torrents = parser.parseDOMDocument(new ZooqleDOMDocument(webPage));
        if (torrents == null) {
            return new ArrayList<>();
        } else {
            return torrents;
        }
    }
}
