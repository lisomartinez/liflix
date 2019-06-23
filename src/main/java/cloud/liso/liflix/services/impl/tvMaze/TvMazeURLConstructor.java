package cloud.liso.liflix.services.impl.tvMaze;

public interface TvMazeURLConstructor {
    String getBaseURL();

    String getShowByIdURL(int id);

    String getShowsPageURL(int page);

    String getSearchShowByName(String name);

    String getSingleSearchByName(String name);

    String getShowSeasonsURL(int showId);

    String getEpisodesURL(int seasonId);
}
