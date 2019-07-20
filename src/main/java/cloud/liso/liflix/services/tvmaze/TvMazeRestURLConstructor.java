package cloud.liso.liflix.services.tvmaze;

import org.springframework.stereotype.Component;

@Component
public class TvMazeRestURLConstructor implements TvMazeURLConstructor {

    private static final String UPDATES = "http://api.tvmaze.com/updates/shows";
    private static final String SHOW = "http://api.tvmaze.com/shows/";
    private static final String SEASONS = "/seasons";
    private static final String EPISODES = "/episodes?specials=1";

    @Override
    public String getUpdatesURL() {
        return UPDATES;
    }

    @Override
    public String getShowSeasonsURL(int showId) {
        return SHOW + showId + SEASONS;
    }

    @Override
    public String getEpisodesURL(int showId) {
        return SHOW + showId + EPISODES;
    }

}
