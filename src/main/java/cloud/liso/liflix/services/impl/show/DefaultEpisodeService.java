package cloud.liso.liflix.services.impl.show;

import cloud.liso.liflix.model.show.Episode;
import cloud.liso.liflix.repositories.EpisodeRepository;
import cloud.liso.liflix.services.api.show.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEpisodeService implements EpisodeService {

    private EpisodeRepository episodeRepository;

    @Autowired
    public DefaultEpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    @Override
    public Episode getEpisode(int showId, int seasonNumber, int episodeNumber) {
        return null;
    }

    @Override
    public Episode getLastEpisode(int showId) {
        return null;
    }
}
