package cloud.liso.liflix.services.torrent.zooqle;

import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.httpClient.TorrentURLConstructor;
import cloud.liso.liflix.services.httpClient.WebClient;
import cloud.liso.liflix.services.httpClient.WebPage;
import cloud.liso.liflix.services.torrent.EpisodeParser;
import cloud.liso.liflix.services.torrent.zooqle.client.ZooqleDOMDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZooqleTorrentFetcher {

    private TorrentURLConstructor torrentUrlConstructor;

    private WebClient client;

    private EpisodeParser parser;

    @Autowired
    public ZooqleTorrentFetcher(@Qualifier("zooqleHttpTorrentURLConstructor") TorrentURLConstructor torrentUrlConstructor,
                                @Qualifier("zooqleWebClient") WebClient client,
                                @Qualifier("zooqleEpisodeParser") EpisodeParser parser) {
        this.torrentUrlConstructor = torrentUrlConstructor;
        this.client = client;
        this.parser = parser;
    }

    public List<Torrent> torrentsFrom(Request request) {
        String url = torrentUrlConstructor.getTorrentsPageURL(request.getShowName(),
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
