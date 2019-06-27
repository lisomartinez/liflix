package cloud.liso.liflix.services.tvMaze;

public interface TvMazeURLConstructor {
    String getUpdatesURL();

    String getShowSeasonsURL(int showId);

    String getEpisodesURL(int seasonId);
}
