package cloud.liso.liflix.torrentfinder.utils;

import cloud.liso.liflix.model.torrent.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class TorrentFactory {
    public static Torrent createTorrent() {
        return Torrent.builder()
                .title("Under The Dome S03E012")
                .size(Size.of(1024, "MB"))
                .codec(Codec.of("X264"))
                .resolution(Resolution.of("1080p"))
                .releaseType(ReleaseType.of("WEB-DL"))
                .magnetLink("magnet:?xt=urn:btih:5092B9075E02FA76D82671014A4C704DF6C9E095&dn=%5Bzooqle.com%5D%20Under%20the%20Dome%20S03E12%20720p%20HDTV%20X264-DIMENSION&tr=http://sugoi.pomf.se/announce&tr=http://explodie.org:6969/announce&tr=http://tracker.pomf.se/announce&tr=http://tracker1.itzmx.com:8080/announce&tr=http://open.acgtracker.com:1096/announce")
                .seeders(3)
                .leechers(1)
                .build();
    }

    public static TorrentList createTorrentInfo() {
        return TorrentList.builder()
                .showId(1)
                .seasonNumber(3)
                .episodeNumber(12)
                .torrents(new ArrayList<>(asList(createTorrent())))
                .lastUpdate(LocalDateTime.of(2010, 2, 12, 21, 0))
                .build();
    }
}
