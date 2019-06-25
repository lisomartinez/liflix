package cloud.liso.liflix.services.show;


import cloud.liso.liflix.model.show.Episode;

public interface EpisodeService {
    Episode getEpisode(int showId, int seasonNumber, int episodeNumber);

    Episode getLastEpisode(int showId);
}
