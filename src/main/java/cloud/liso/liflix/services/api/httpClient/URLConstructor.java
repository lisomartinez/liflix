package cloud.liso.liflix.services.api.httpClient;

public interface URLConstructor {
    String getTorrentsPageURL(String torrentName, int seasonNumber, int episodeNumber);
}
