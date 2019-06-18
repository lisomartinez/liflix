package cloud.liso.liflix.controllers.shows;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShowController_IT_ {
//
//    private String baseUrl;
//
//    @Autowired
//    private TestRestTemplate rest;
//
//    @Autowired
//    private ServerProperties serverProperties;
//
//    @Autowired
//    private AccessTokenFactory tokenFactory;
//
//    @LocalServerPort
//    private int port;
//
//    @BeforeEach
//    void setUp() {
//        baseUrl = "httpClient:/" + serverProperties.getAddress() + ":" + port;
//    }
//
//    @Test
//    void getShow_validShowId_ShouldReturnShow() throws IOException {
//        String url = baseUrl + ShowController.SHOWS + ShowController.SHOW_ID;
//        HttpEntity entity = tokenFactory.entityWithAccessTokenHeader(baseUrl);
//
//        Show expected = ShowFactory.getShowWithGenreAndSchedule(LocalDateTime.now(), "Ended");
//        ResponseEntity<String> response = rest.exchange(url, GET, entity, String.class, 1);
//        assertThat(response.getBody()).isNotNull();
//    }
//
//    @Test
//    void getShow_ShowIdEqualsToZero_ShouldReturnStatusNotFound() throws IOException {
//        String url = baseUrl + ShowController.SHOWS + ShowController.SHOW_ID;
//        HttpEntity entity = tokenFactory.entityWithAccessTokenHeader(baseUrl);
//
//        ResponseEntity<String> response = rest.exchange(url, GET, entity, String.class, 0);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void getShow_InvalidShowId_ShouldReturnStatusNotFound() throws IOException {
//        String url = baseUrl + ShowController.SHOWS + ShowController.SHOW_ID;
//        HttpEntity entity = tokenFactory.entityWithAccessTokenHeader(baseUrl);
//
//        ResponseEntity<String> response = rest.exchange(url, GET, entity, String.class, Integer.MAX_VALUE);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void getShowCalledTwoTimes_validShowId_ShouldReturnLastUpdateOfFirstShow() throws IOException {
//        String url = baseUrl + ShowController.SHOWS + ShowController.SHOW_ID;
//        HttpEntity entity = tokenFactory.entityWithAccessTokenHeader(baseUrl);
//
//
//        Show expected = ShowFactory.getShowWithGenreAndSchedule(LocalDateTime.now(), "Ended");
//        Show response = rest.exchange(url, GET, entity, Show.class, 1).getBody();
//
////        LogManager.getLogger().info(response);
//
//        expected.setLastUpdate(LocalDateTime.of(response.getLastUpdate().toLocalDate(), response.getLastUpdate().toLocalTime()));
//
//        assertThat(response.getLastUpdate()).isEqualTo(expected.getLastUpdate());
//
//        Show result2 = rest.exchange(url, GET, entity, Show.class, 1).getBody();
//        assertThat(result2.getLastUpdate()).isEqualTo(expected.getLastUpdate());
//    }
//
//    @Test
//    void getSeason_ValidShowIdAndValidSeasonId() throws IOException {
//
//        String url = baseUrl + ShowController.SHOWS + ShowController.SHOW_ID +
//                ShowController.SEASON + ShowController.SEASON_NUMBER;
//
//        HttpEntity entity = tokenFactory.entityWithAccessTokenHeader(baseUrl);
//
//        Season seasonThree = ShowFactory.getLastSeasonWithEpisodes();
//
//        Season response = rest.exchange(url, GET, entity, Season.class, 1, 3).getBody();
//
//        assertThat(response).isEqualTo(seasonThree);
//    }


}
