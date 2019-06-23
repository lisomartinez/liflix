package cloud.liso.liflix.services.impl.tvMaze;

import cloud.liso.liflix.dto.TvMazeEpisodeDto;
import cloud.liso.liflix.dto.TvMazeSeason;
import cloud.liso.liflix.dto.TvMazeShowDto;
import cloud.liso.liflix.exceptions.TvMazeResourceNotFoundException;
import cloud.liso.liflix.exceptions.TvMazeSeasonNotFoundException;
import cloud.liso.liflix.exceptions.TvMazeShowNotFoundException;
import cloud.liso.liflix.model.show.Episode;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.model.show.Show;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TvMazeServiceImpl implements TvMazeService {

    private TvMazeClient client;

    private ModelMapper modelMapper;

    @Autowired
    public TvMazeServiceImpl(TvMazeClient client, ModelMapper modelMapper) {
        this.client = client;
        this.modelMapper = modelMapper;
    }

    @Override
    public Show getShowById(int id) {
        try {
            TvMazeShowDto show = client.getShowById(id);
            return modelMapper.map(show, Show.class);
        } catch (TvMazeResourceNotFoundException ex) {
            throw new TvMazeShowNotFoundException("Tv Maze Show id=" + id + " Not Found");
        }
    }

    @Override
    public List<Season> getSeasons(int showId) {
        try {
            List<TvMazeSeason> seasons = client.getShowSeasons(showId);
            return seasons.stream()
                    .map(season -> modelMapper.map(season, Season.class))
                    .collect(Collectors.toList());
        } catch (TvMazeResourceNotFoundException ex) {
            throw new TvMazeSeasonNotFoundException("TV Maze Show id=" + showId + " seasons not found");
        }
    }

    @Override
    public List<Episode> getEpisodes(int seasonId) {
        try {
            List<TvMazeEpisodeDto> episodes = client.getEpisodes(seasonId);
            return episodes.stream()
                    .map(episode -> modelMapper.map(episode, Episode.class))
                    .collect(Collectors.toList());
        } catch (TvMazeResourceNotFoundException ex) {
            throw new TvMazeSeasonNotFoundException("Tv Maze season id=" + seasonId + "not found");
        }
    }

}
 