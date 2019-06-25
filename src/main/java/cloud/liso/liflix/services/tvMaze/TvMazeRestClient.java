package cloud.liso.liflix.services.tvMaze;

import cloud.liso.liflix.dto.ShowUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
            throw new NotImplementedException();
        }
        return forEntity.getBody();
    }
}
