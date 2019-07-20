package cloud.liso.liflix.services.imdb;

import cloud.liso.liflix.exceptions.WebClientErrorException;
import cloud.liso.liflix.exceptions.WebPageNotFoundException;
import cloud.liso.liflix.services.http_client.HttpWebClient;
import cloud.liso.liflix.services.http_client.WebPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImdbWebClientTest {

    @Test
    void fetchWebPage_validURL_ReturnsWebPage() {
        WebPage response = new HttpWebClient().fetchWebPage("https://www.imdb.com/title/tt1553656");
        assertThat(response.getContent().length()).isNotEqualTo(0);
    }

    @Test
    void fetchWebPage_invalidShowId_ThrowsImdbShowNotFoundException() {
        assertThrows(WebPageNotFoundException.class,
                () -> new HttpWebClient().fetchWebPage("https://www.imdb.com/title/tt1553656111"));
    }

    @Test
    void fetchWebPage_invalidUrl_ThrowsImdbWebClientErrorException() {
        assertThrows(WebClientErrorException.class,
                () -> new HttpWebClient().fetchWebPage("https://www.imdb.cm/title/tt1553656111"));
    }
}