package cloud.liso.liflix;

import cloud.liso.liflix.services.tvMaze.TvMazeClient;
import cloud.liso.liflix.services.tvMaze.TvMazeRestRespondeErrorHandler;
import cloud.liso.liflix.services.tvMaze.TvMazeURLConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;

@RestClientTest({TvMazeClient.class,
        TvMazeURLConstructor.class,
        TvMazeRestRespondeErrorHandler.class,
})
class TvMazeClienTest {

    private final static String BASE_URL = "http://api.tvmaze.com/";
    private final static String SHOWS = "shows/";
    private final static String SHOWs_PAGE = "shows?page=";
    private final static String SEARCH = "search/shows?q=";
    private final static String SINGLE_SEARCH = "singlesearch/shows?q=";
    private final static String SEASONS = "/seasons";

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("tvMazeRestClient")
    private TvMazeClient tvMazeRestClient;


    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();
    }

//    @Test
//    void getById_ValidId_ReturnsShowDto() throws IOException {
//
//        setUpGetById();
//
//        TvMazeShowDto expected = TvMazeShowDto.builder()
//                .id(1)
//                .name("Under the Dome")
//                .type("Scripted")
//                .language("English")
//                .genres(Arrays.asList(GenreDto.of("Drama"), GenreDto.of("Science-Fiction"), GenreDto.of("Thriller")))
//                .status("Ended")
//                .runtime(60)
//                .premiered(LocalDate.of(2013, 6, 24))
//                .officialSite("http://www.cbs.com/shows/under-the-dome/")
//                .schedule(TvMazeScheduleDto.of(new ArrayList<>(Arrays.asList(DayOfWeek.of(DayOfWeek.THURSDAY))), LocalTime.of(22, 0)))
//                .tvMazeUrl("http://www.tvmaze.com/shows/1/under-the-dome")
//                .imdbUrl("https://www.imdb.com/title/tt1553656")
//                .imageUrl("http://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg")
//                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.")
//                .build();
//
//
//        TvMazeShowDto response = tvMazeRestClient.getShowById(expected.getId());
//
//        assertThat(response).isEqualTo(expected);
//    }
//
//    @Test
//    void getById_InvalidId_ThrowsShowNotFoundException() {
//        assertThrows(TvMazeResourceNotFoundException.class, () -> tvMazeRestClient.getShowById(0));
//    }
//
//
//    @Test
//    @Disabled
//    void MoreThan20CallsEvery10_ThrowsTvMazeTooManyRequestException() {
//        List<TvMazeShowDto> tvMazeShowDtos = new ArrayList<>();
//        for (int i = 1; i <= 400; i++) {
//            tvMazeShowDtos.add(tvMazeRestClient.getShowById(1));
//        }
//        System.out.println(tvMazeShowDtos);
//        assertThat(tvMazeShowDtos.size()).isEqualTo(400);
//    }
//
//    @Test
//    void getShowSeasons_validShow_ShouldReturnListOfSeasons() throws IOException {
//        File showSeasons = JsonTestFiles.getShowSeasons();
//        final int showId = 1;
//        setUpShowSeasons();
//        List<TvMazeSeason> expected = objectMapper.readValue(showSeasons, new TypeReference<List<TvMazeSeason>>() {
//        });
//        List<TvMazeSeason> response = tvMazeRestClient.getShowSeasons(showId);
//
//        assertThat(response.size()).isEqualTo(expected.size());
//    }
//
//    @Test
//    void getShow_NonExistentShow_ShouldThrowTvMazeShowNotFound() {
//        server.expect(requestTo(BASE_URL + SHOWS + "1000000000")).andRespond(withStatus(HttpStatus.NOT_FOUND));
//        assertThrows(TvMazeResourceNotFoundException.class, () -> tvMazeRestClient.getShowById(1000000000));
//    }
//
//    private void setUpGetById() throws IOException {
//        String expected = Files.readString(Paths.get(JsonTestFiles.getShow().getAbsolutePath()));
//        server.expect(requestTo(BASE_URL + SHOWS + "1")).andRespond(withSuccess(expected, MediaType.APPLICATION_JSON));
//    }
//
//    private void setUpShowSeasons() throws IOException {
//        String expected = Files.readString(Paths.get(JsonTestFiles.getShowSeasons().getAbsolutePath()));
//        server.expect(requestTo(BASE_URL + SHOWS + "1" + SEASONS)).andRespond(withSuccess(expected, MediaType.APPLICATION_JSON));
//    }
//
//    private void setUpShowPage(File showPage0) throws IOException {
//        String expected = Files.readString(Paths.get(showPage0.getAbsolutePath()));
//        server.expect(requestTo(BASE_URL + SHOWs_PAGE + "0")).andRespond(withSuccess(expected, MediaType.APPLICATION_JSON));
//    }
}