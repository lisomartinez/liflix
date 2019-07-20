package cloud.liso.liflix.services.tvmaze;

import cloud.liso.liflix.dto.ShowUpdateDto;
import cloud.liso.liflix.dto.TvMazeEpisodeDto;
import cloud.liso.liflix.dto.TvMazeSeasonDto;

import java.util.List;
import java.util.Map;

public interface TvMazeClient {
    ShowUpdateDto getUpdates();

    List<TvMazeSeasonDto> getShowSeasons(int showId);

    Map<Integer, List<TvMazeEpisodeDto>> getSeasonEpisodes(int showId);

}
