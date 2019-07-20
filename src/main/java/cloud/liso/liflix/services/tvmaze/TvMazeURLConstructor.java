package cloud.liso.liflix.services.tvmaze;

public interface TvMazeURLConstructor {
    String getUpdatesURL();

    String getShowSeasonsURL(int showId);

    String getEpisodesURL(int seasonId);
}
