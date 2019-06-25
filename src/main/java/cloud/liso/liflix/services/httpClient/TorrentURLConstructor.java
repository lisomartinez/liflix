package cloud.liso.liflix.services.httpClient;

public interface TorrentURLConstructor {
    String getTorrentsPageURL(String torrentName, int seasonNumber, int episodeNumber);
}
