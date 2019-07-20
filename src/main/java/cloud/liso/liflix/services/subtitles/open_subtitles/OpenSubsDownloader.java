package cloud.liso.liflix.services.subtitles.open_subtitles;

import cloud.liso.liflix.dto.OpenSubtitlesSubtitleDto;
import cloud.liso.liflix.exceptions.OpenSubsListingErrorException;
import cloud.liso.liflix.services.subtitles.RequestSubtitleDto;
import cloud.liso.liflix.services.subtitles.SubtitleClient;
import cloud.liso.liflix.services.subtitles.SubtitleDownloader;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenSubsDownloader implements SubtitleDownloader {

    private final ObjectFactory<SubtitleClient> clientBuilder;

    private RestTemplate restTemplate;

    @Autowired
    public OpenSubsDownloader(ObjectFactory<SubtitleClient> clientBuilder, RestTemplateBuilder restTemplateBuilder) {
        this.clientBuilder = clientBuilder;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<OpenSubtitlesSubtitleDto> getList(@NotNull RequestSubtitleDto req) {
        String url = clientBuilder.getObject().from(req).build();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        headers.add("X-User-Agent", "TemporaryUserAgent");
        try {
            ResponseEntity<List<OpenSubtitlesSubtitleDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<OpenSubtitlesSubtitleDto>>() {
                    });
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            throw new OpenSubsListingErrorException(url);
        }
    }
}
