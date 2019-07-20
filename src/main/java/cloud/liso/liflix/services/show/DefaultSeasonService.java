package cloud.liso.liflix.services.show;

import cloud.liso.liflix.exceptions.SeasonNotFoundException;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.repositories.SeasonRepository;
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
        List<Season> seasons = seasonRepository.findAllByShow(showId);
        if (seasons.isEmpty()) throw new ShowNotFoundException(String.valueOf(showId));
        return seasons;
    }

    @Override
    public Season getSeasonByNumber(int showId, int seasonNumber) {
        return seasonRepository.getSeasonsByShowId(showId, seasonNumber)
                .orElseThrow(() -> new SeasonNotFoundException("Show id=" + showId +
                        " Season number=" + seasonNumber + " not found"));
    }
}
