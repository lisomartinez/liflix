package cloud.liso.liflix.services.impl.show;

import cloud.liso.liflix.exceptions.SeasonNotFoundException;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.repositories.SeasonRepository;
import cloud.liso.liflix.services.api.show.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultSeasonService implements SeasonService {

    private SeasonRepository seasonRepository;

    @Autowired
    public DefaultSeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public List<Season> getAllSeasons(int showId) {
        return seasonRepository.findAllByShow(showId);
    }

    @Override
    public Season getSeasonByNumber(int showId, int seasonNumber) {
        return seasonRepository.getSeasonsByShowId(showId, seasonNumber)
                .orElseThrow(() -> new SeasonNotFoundException("Show id=" + showId +
                        " Season number=" + seasonNumber + " not found"));
    }
}
