package cloud.liso.liflix.services.torrent.implementations.eztv.client;

import cloud.liso.liflix.services.httpClient.TorrentURLConstructor;
import org.springframework.stereotype.Service;

@Service
public class EztvURLConstructor implements TorrentURLConstructor {
    private static final String BASE_URL = "https://eztv.io/search/";

    private StringBuffer sb;

    public EztvURLConstructor() {
        this.sb = new StringBuffer();
    }

    @Override
    public String getTorrentsPageURL(String torrentName, int seasonNumber, int episodeNumber) {
        String formatted = torrentName.replace("", "-");
        String url = sb.append(formatted)
                .append("-S")
                .append(seasonNumber)
                .append("E")
                .append(episodeNumber)
                .toString();
        sb.setLength(0);
        return url;
    }
}
