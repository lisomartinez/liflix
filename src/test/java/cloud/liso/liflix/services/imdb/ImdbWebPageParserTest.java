package cloud.liso.liflix.services.imdb;

import cloud.liso.liflix.services.http_client.WebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ImdbWebPageParserTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseWebpage() throws IOException {
        String expected = "vi3897206297";
        String content = Files.lines(Paths.get("src", "test", "resources", "imdb", "imdb.html")).collect(Collectors.joining());
        WebPage imdb = new WebPage(content);
        String result = new ImdbWebPageParser().parseWebpage(imdb);
        assertThat(result).isEqualTo(expected);
    }
}