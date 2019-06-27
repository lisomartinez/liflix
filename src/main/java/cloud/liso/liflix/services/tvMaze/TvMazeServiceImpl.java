package cloud.liso.liflix.services.tvMaze;

import cloud.liso.liflix.dto.ShowUpdateDto;
import cloud.liso.liflix.dto.TvMazeEpisodeDto;
import cloud.liso.liflix.dto.TvMazeSeasonDto;
import cloud.liso.liflix.model.show.Episode;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.repositories.ShowRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class TvMazeServiceImpl implements TvMazeService {

    private TvMazeClient client;
    private static final int TTL = 10;
    private ShowRepository showRepository;
    private ModelMapper modelMapper;
    private Predicate<Map.Entry<Integer, LocalDateTime>> isOutDated =
            e -> Duration.between(e.getValue(), LocalDateTime.now()).toMinutes() > TTL;

    @Autowired
    public TvMazeServiceImpl(TvMazeClient client, ShowRepository showRepository, ModelMapper modelMapper) {
        this.client = client;
        this.showRepository = showRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void update() {
        updatedShows()
                .stream()
                .map(this::updateSeasons)
                .forEach(this::updateEpisodes);
    }

    private Show updateSeasons(Show show) {
        List<TvMazeSeasonDto> showSeasons = client.getShowSeasons(show.getId());
        List<Season> seasons = showSeasons
                .stream()
                .map(s -> modelMapper.map(s, Season.class))
                .collect(toList());
        show.setSeasons(seasons);
        return show;
    }

    private void updateEpisodes(Show show) {
        Map<Integer, List<TvMazeEpisodeDto>> episodes = client.getSeasonEpisodes(show.getId());

        show.getSeasons()
                .forEach(s -> s.setEpisodes(converToEntities(episodes, s.getNumber())));
    }

    private List<Episode> converToEntities(Map<Integer, List<TvMazeEpisodeDto>> episodes, int seasonNumber) {
        return episodes
                .get(seasonNumber)
                .stream()
                .map(e -> modelMapper.map(e, Episode.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Show> updatedShows() {
        ShowUpdateDto updates = client.getUpdates();
        return updates
                .getShows()
                .entrySet()
                .stream()
                .filter(isOutDated)
                .map(Map.Entry::getKey)
                .map(showRepository::findByTvMazeId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }



}
 