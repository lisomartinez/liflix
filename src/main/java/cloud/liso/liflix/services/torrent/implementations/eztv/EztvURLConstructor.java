package cloud.liso.liflix.services.torrent.implementations.eztv;

import cloud.liso.liflix.services.http_client.TorrentURLConstructor;
import org.springframework.stereotype.Service;

@Service
public class EztvURLConstructor implements TorrentURLConstructor {
    private static final String BASE_URL = "https://eztv.io/search/";

    private StringBuilder sb;

    public EztvURLConstructor() {
        this.sb = new StringBuilder();
    }

    @Override
    public String getTorrentsPageURL(String torrentName, int seasonNumber, int episodeNumber) {
        String formatted = torrentName.replace(" ", "-");
        String season = seasonNumber < 10 ? "0" + seasonNumber : String.valueOf(seasonNumber);
        String episode = episodeNumber < 10 ? "0" + episodeNumber : String.valueOf(episodeNumber);
        String url = sb.append(BASE_URL).append(formatted)
                .append("-S")
                .append(season)
                .append("E")
                .append(episode)
                .toString();
        sb.setLength(0);
        return url;
    }
}
