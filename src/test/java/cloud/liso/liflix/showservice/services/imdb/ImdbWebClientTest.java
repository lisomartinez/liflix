package cloud.liso.liflix.showservice.services.imdb;

import cloud.liso.liflix.exceptions.ImdbShowNotFoundException;
import cloud.liso.liflix.exceptions.ImdbWebClientErrorException;
import cloud.liso.liflix.services.httpClient.WebPage;
import cloud.liso.liflix.services.imdb.ImdbWebClient;
import cloud.liso.liflix.showservice.utils.ShowFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImdbWebClientTest {

    @Test
    void fetchWebPage_validURL_ReturnsWebPage() {
        WebPage response = new ImdbWebClient().fetchWebPage(ShowFactory.getImdb());
        assertThat(response.getContent().length()).isNotEqualTo(0);
    }

    @Test
    void fetchWebPage_invalidShowId_ThrowsImdbShowNotFoundException() {
        assertThrows(ImdbShowNotFoundException.class, () -> new ImdbWebClient().fetchWebPage("https://www.imdb.com/title/tt1553656111"));
    }

    @Test
    void fetchWebPage_invalidUrl_ThrowsImdbWebClientErrorException() {
        assertThrows(ImdbWebClientErrorException.class, () -> new ImdbWebClient().fetchWebPage("https://www.imdb.cm/title/tt1553656111"));
    }
}