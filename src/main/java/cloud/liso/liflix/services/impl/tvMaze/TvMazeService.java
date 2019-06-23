package cloud.liso.liflix.services.impl.tvMaze;


import cloud.liso.liflix.model.show.Episode;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.model.show.Show;

import java.util.List;

public interface TvMazeService {

    Show getShowById(int id);

    List<Season> getSeasons(int showId);

    List<Episode> getEpisodes(int seasonId);
}
