package cloud.liso.liflix.services.tvMaze;

import org.springframework.stereotype.Component;

@Component
public class TvMazeRestURLConstructor implements TvMazeURLConstructor {

    private static final String BASE_URL = "http://api.tvmaze.com/";
    private static final String UPDATES = "http://api.tvmaze.com/updates/shows";
    private static final String SHOWS = "shows/";
    private static final String SHOWS_PAGE = "shows?page=";
    private static final String SEARCH = "search/shows?q=";
    private static final String SEASONS = "seasons";
    private static final String EPISODES = "/episodes";

    private StringBuilder sb;

    public TvMazeRestURLConstructor() {
        this.sb = new StringBuilder();
    }

    @Override
    public String getUpdatesURL() {
        return UPDATES;
    }

}
