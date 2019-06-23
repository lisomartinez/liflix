package cloud.liso.liflix.services.impl.openSubtitles;

import cloud.liso.liflix.dto.SubtitleDto;
import cloud.liso.liflix.exceptions.OpenSubsListingErrorException;
import cloud.liso.liflix.services.api.subtitles.SubtitleClient;
import cloud.liso.liflix.services.api.subtitles.SubtitleDownloader;
import cloud.liso.liflix.showservice.RequestSubtitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenSubsDownloader implements SubtitleDownloader {

    private SubtitleClient client;

    private RestTemplate restTemplate;

    @Autowired
    public OpenSubsDownloader(SubtitleClient client, RestTemplateBuilder restTemplateBuilder) {
        this.client = client;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<? extends SubtitleDto> getList(RequestSubtitleDto req) {
        String url = client.from(req).build();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        headers.add("X-User-Agent", "TemporaryUserAgent");
        ResponseEntity<List<? extends SubtitleDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Op>>() {
                });

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new OpenSubsListingErrorException(url);
        }
        return response.getBody();
    }
}
