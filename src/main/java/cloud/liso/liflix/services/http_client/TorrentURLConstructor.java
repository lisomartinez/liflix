package cloud.liso.liflix.services.http_client;

public interface TorrentURLConstructor {
    String getTorrentsPageURL(String torrentName, int seasonNumber, int episodeNumber);
}
