package cloud.liso.liflix.services.impl.tvMaze;

import cloud.liso.liflix.dto.TvMazeEpisodeDto;
import cloud.liso.liflix.dto.TvMazeSeason;
import cloud.liso.liflix.dto.TvMazeShowDto;

import java.util.List;

public interface TvMazeClient {
    List<TvMazeShowDto> findPage(int page);

    List<TvMazeSeason> getShowSeasons(int showId);

    TvMazeShowDto getShowById(int id);

    List<TvMazeEpisodeDto> getEpisodes(int seasonId);
}
