package cloud.liso.liflix.services.torrent.zooqle.parsers;

import cloud.liso.liflix.exceptions.WebClientErrorException;
import cloud.liso.liflix.services.http_client.HttpWebClient;
import cloud.liso.liflix.services.http_client.WebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HttpWebClientTest {
    private HttpWebClient client;

    @BeforeEach
    void setUp() {
        client = new HttpWebClient();
    }

    @Test
    void fetchWebPage_validUrl_ShouldReturnContentAsString() throws IOException {
        String episodeUrl = "https://zooqle.com/search?q=under+the+dome+s03e13";
//        String episodeUrl = "https://zoogle.com/search?q=under+the+dome+S03E12 ";
        WebPage response = client.fetchWebPage(episodeUrl);

        assertThat(response.getContent()).isNotBlank();
    }

    @Test
    void fetchWebPage_invalidURL_ShouldThrowWebClientErrorException() {
        String episodeUrl = "https://ooqle.com/search?q=under+the+dome+s03e13";
        assertThrows(WebClientErrorException.class, () -> client.fetchWebPage(episodeUrl));
    }


}