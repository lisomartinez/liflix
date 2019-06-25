package cloud.liso.liflix.services.torrent.zooqle.client;

import cloud.liso.liflix.services.httpClient.TorrentURLConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ZooqleHttpTorrentURLConstructor implements TorrentURLConstructor {
    private static final String BASE_URL = "https://zooqle.com";
    private static final String SEARCH = "https://zooqle.com/search?q=";
    private static final Pattern URL = Pattern.compile("\\s");
    private static final String SPACE = "+";

    private StringBuilder sb;

    public ZooqleHttpTorrentURLConstructor() {
        this.sb = new StringBuilder();
    }

    @Override
    public String getTorrentsPageURL(String torrentName, int seasonNumber, int episodeNumber) {
        String name = URL.matcher(torrentName).replaceAll(SPACE);
        String season = seasonNumber < 10 ? "0" + seasonNumber : String.valueOf(seasonNumber);
        String episode = episodeNumber < 10 ? "0" + episodeNumber : String.valueOf(episodeNumber);

        String url = sb.append(SEARCH).append(name).append(SPACE)
                .append('s').append(season)
                .append('e').append(episode)
                .toString();
        sb.setLength(0);
        return url;
    }
}
