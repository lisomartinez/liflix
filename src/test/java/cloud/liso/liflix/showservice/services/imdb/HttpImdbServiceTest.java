package cloud.liso.liflix.showservice.services.imdb;

import cloud.liso.liflix.exceptions.ImdbNotAvailableException;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.repositories.ShowRepository;
import cloud.liso.liflix.services.httpClient.WebClient;
import cloud.liso.liflix.services.httpClient.WebPage;
import cloud.liso.liflix.services.httpClient.WebPageParser;
import cloud.liso.liflix.services.imdb.HttpImdbService;
import cloud.liso.liflix.services.imdb.ImdbService;
import cloud.liso.liflix.showservice.utils.ShowFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HttpImdbServiceTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private WebClient client;

    @Mock
    private WebPageParser parser;

    private ImdbService service;

    @BeforeEach
    void setUp() {
        service = new HttpImdbService(client, parser, showRepository);
    }

    @Test
    void getImdbVideoLink_validShowIdAndShowWithImdbLink_returnsImdbLink() throws IOException {
        String imdb = ShowFactory.getImdbVideoFullURL();
        String imdbVideo = ShowFactory.getImdbVideo();
        Path path = Paths.get("src", "test", "resources", "imdb", "imdb.html");
        String content = Files.lines(path).collect(Collectors.joining());
        WebPage page = new WebPage(content);

        when(showRepository.getImdbURL(anyInt())).thenReturn(Optional.of(imdb));
        when(client.fetchWebPage(anyString())).thenReturn(new WebPage(content));
        when(parser.parseWebpage(page)).thenReturn(imdbVideo);

        String result = service.getImdbVideoLink(1);
        assertThat(result).isEqualTo(imdb);
    }

    @Test
    void getImdbVideoLink_validShowIdWithNotAvailableLink_throwsImdbNotAvailableException() {
        final String NOT_AVAILABLE = "N/A";
        when(showRepository.getImdbURL(anyInt())).thenReturn(Optional.of(NOT_AVAILABLE));
        assertThrows(ImdbNotAvailableException.class, () -> service.getImdbVideoLink(1));
    }

    @Test
    void getImdbVideoLink_InvalidShowId_ThrowsShowNotFoundException() {
        when(showRepository.getImdbURL(anyInt())).thenReturn(Optional.empty());
        assertThrows(ShowNotFoundException.class, () -> service.getImdbVideoLink(Integer.MAX_VALUE));
    }
}