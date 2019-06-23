package cloud.liso.liflix.services.impl.tvMaze;

import cloud.liso.liflix.dto.TvMazeEpisodeDto;
import cloud.liso.liflix.dto.TvMazeSeason;
import cloud.liso.liflix.dto.TvMazeShowDto;
import cloud.liso.liflix.exceptions.TvMazeResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class TvMazeRestClient implements TvMazeClient {

    private RestTemplate restTemplate;
    private TvMazeURLConstructor tvMazeURLConstructor;
    private ObjectMapper objectMapper;

    @Autowired
    public TvMazeRestClient(RestTemplateBuilder restTemplateBuilder,
                            TvMazeURLConstructor URLConstructor,
                            TvMazeRestRespondeErrorHandler errorHandler, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplateBuilder
                .errorHandler(errorHandler)
                .build();
        this.tvMazeURLConstructor = URLConstructor;
    }

    @Override
    public TvMazeShowDto getShowById(int id) {
        try {
            String url = tvMazeURLConstructor.getShowByIdURL(id);
            return restTemplate.getForObject(url, TvMazeShowDto.class);
        } catch (IllegalArgumentException ex) {
            throw new TvMazeResourceNotFoundException("Show id invalid " + id);
        }
    }

    @Override
    public List<TvMazeShowDto> findPage(int page) {
        try {
            String url = tvMazeURLConstructor.getShowsPageURL(page);
            ResponseEntity<List<TvMazeShowDto>> response = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    new ParameterizedTypeReference<List<TvMazeShowDto>>() {
                    });
            return response.getBody();
        } catch (IllegalArgumentException ex) {
            throw new TvMazeResourceNotFoundException("Show page id invalid " + page);
        }
    }

    @Override
    public List<TvMazeSeason> getShowSeasons(int showId) {
        try {
            String url = tvMazeURLConstructor.getShowSeasonsURL(showId);
            ResponseEntity<List<TvMazeSeason>> response = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    new ParameterizedTypeReference<List<TvMazeSeason>>() {
                    });
            return response.getBody();
        } catch (IllegalArgumentException ex) {
            throw new TvMazeResourceNotFoundException("Show id invalid " + showId);
        }
    }

    @Override
    public List<TvMazeEpisodeDto> getEpisodes(int seasonId) {
        try {
            String url = tvMazeURLConstructor.getEpisodesURL(seasonId);

//            ResponseEntity<String> res = restTemplate.exchange(
//                    url,
//                    GET,
//                    null,
//                    String.class);
//
//            List<TvMazeEpisodeDto> episodeDtoList = objectMapper.readValue(res.getBody(),new TypeReference<List<TvMazeEpisodeDto>>() { });
//            return episodeDtoList;

            ResponseEntity<List<TvMazeEpisodeDto>> res = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    new ParameterizedTypeReference<List<TvMazeEpisodeDto>>() {
                    });

            return res.getBody();

        } catch (IllegalArgumentException ex) {
            throw new TvMazeResourceNotFoundException("Season id=" + "inavlid");
        }
    }
}
