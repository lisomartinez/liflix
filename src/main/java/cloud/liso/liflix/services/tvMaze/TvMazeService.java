package cloud.liso.liflix.services.tvMaze;


import cloud.liso.liflix.model.show.Show;

import java.util.List;

public interface TvMazeService {
    List<Show> updatedShows();

    void update();
}
