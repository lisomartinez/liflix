package cloud.liso.liflix.showservice;

import org.junit.jupiter.api.Test;

public class SubtitleClientTest {
    @Test
    void connect_establishConnection() {
    }

    @Test
    void getSubtitlesByIMDB() throws Exception {
        /*
        parsing (number)
        imdbid (always format it as sprintf("%07d", $imdb) - when using imdb you can add /tags-hdtv for example.
        moviebytesize (number)
        moviehash (should be always 16 character, must be together with moviebytesize)
        query (use url_encode, make sure " " is converted to "%20")
        season (number)
        sublanguageid (if ommited, all languages are returned)
        tag (use url_encode, make sure " " is converted to "%20")
         */
        String expected = "https://rest.opensubtitles.org/search/episode-20/imdbid-4145054/moviebytesize-750005572/moviehash-319b23c54e9cf314/season-2/sublanguageid-eng";
//        String showImdb = "https://www.imdb.com/title/tt1553656";
//        String imdb = showImdb.substring(showImdb.lastIndexOf("t") + 1);

//        assertThat(imdb).isEqualTo("1553656");
//        SubtitleClient client = new OpenSubsRestClient(fields);
//        String url = client.byEpisode(20)
//                .byImdb("4145054")
//                .byMovieByteSize(750005572L)
//                .byMovieHash("319b23c54e9cf314")
////                .byQuery("")
//                .bySeason(2)
//                .bySubLanguageId("eng")
////                .byTag("")
//                .build();
//        assertThat(url).isEqualTo(expected);
    }
}
