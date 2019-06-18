package cloud.liso.liflix.services.api.show;

import cloud.liso.liflix.model.show.Season;

import java.util.List;

public interface SeasonService {
    List<Season> getAllSeasons(int showId);

    Season getSeasonByNumber(int showId, int seasonNumber);
}
