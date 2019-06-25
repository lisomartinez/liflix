package cloud.liso.liflix.services.tvMaze;


import cloud.liso.liflix.dto.ShowUpdateDto;

public interface TvMazeService {
    ShowUpdateDto updatedShows(int ttl);
}
