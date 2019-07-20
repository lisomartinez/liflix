package cloud.liso.liflix.services.tvmaze;

import cloud.liso.liflix.dto.ShowUpdateDto;
import cloud.liso.liflix.dto.TvMazeEpisodeDto;
import cloud.liso.liflix.dto.TvMazeSeasonDto;
import cloud.liso.liflix.exceptions.TvMazeResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;

@Component
public class TvMazeRestClient implements TvMazeClient {

    private RestTemplate restTemplate;
    private TvMazeURLConstructor tvMazeURLConstructor;

    @Autowired
    public TvMazeRestClient(RestTemplateBuilder restTemplateBuilder,
                            TvMazeURLConstructor URLConstructor,
                            TvMazeRestRespondeErrorHandler errorHandler) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(errorHandler)
                .build();
        this.tvMazeURLConstructor = URLConstructor;
    }

    @Override
    public ShowUpdateDto getUpdates() {
        String url = tvMazeURLConstructor.getUpdatesURL();
        ResponseEntity<ShowUpdateDto> forEntity = restTemplate.getForEntity(url, ShowUpdateDto.class);
        if (forEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Not yet implemented");
        }
        return forEntity.getBody();
    }

    @Override
    public List<TvMazeSeasonDto> getShowSeasons(int showId) {
        try {
            String url = tvMazeURLConstructor.getShowSeasonsURL(showId);
            ResponseEntity<List<TvMazeSeasonDto>> response = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    new ParameterizedTypeReference<List<TvMazeSeasonDto>>() {
                    });
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Not yet implemented");
            }
            return response.getBody();
        } catch (IllegalArgumentException ex) {
            throw new TvMazeResourceNotFoundException("Show id invalid " + showId);
        }
    }

    @Override
    public Map<Integer, List<TvMazeEpisodeDto>> getSeasonEpisodes(int seasonId) {
        try {
            String url = tvMazeURLConstructor.getEpisodesURL(seasonId);
            ResponseEntity<List<TvMazeEpisodeDto>> res = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    new ParameterizedTypeReference<List<TvMazeEpisodeDto>>() {
                    });
            if (res.getStatusCode() != HttpStatus.OK) throw new RuntimeException("Not yet implemented");
            if (res.getBody() == null) throw new RuntimeException("Not yet implemented");
            return res.getBody()
                    .stream()
                    .collect(groupingBy(TvMazeEpisodeDto::getSeason, toList()));

        } catch (IllegalArgumentException ex) {
            throw new TvMazeResourceNotFoundException("Season id=" + "inavlid");
        }
    }
}
