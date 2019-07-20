package cloud.liso.liflix.services.tvmaze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class TvMazServiceTest {

    private static final int SEASON_ID = 6233;

    private TvMazeService tvMazeService;

    @Mock
    private TvMazeClient tvMazeClient;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
//        this.tvMazeService = new TvMazeServiceImpl(tvMazeClient, modelMapper);
    }

//    @Test
//    @DisplayName("Fetch Show by Id should return Show instance with same Id")
//    void fetchShow_ById_shouldReturnShow() {
//        int id = 1;
//        TvMazeShowDto tvMazeShowDto = mock(TvMazeShowDto.class);
//        Show show = mock(Show.class);
//        when(tvMazeClient.getShowById(id)).thenReturn(tvMazeShowDto);
//        when(modelMapper.map(tvMazeShowDto, Show.class)).thenReturn(show);
//
//        Show returnedShow = tvMazeService.getShowById(id);
//        assertThat(returnedShow).isEqualTo(show);
//    }
//
//    @Test
//    @DisplayName("Fetch Non-Existent Show Throws TvMazeShowNotFoundException")
//    void fetchShow_NonExistentShow_shouldThrowShowNotFoundException() {
//        int id = 1;
//        TvMazeShowDto tvMazeShowDto = mock(TvMazeShowDto.class);
//        Show show = mock(Show.class);
//        when(tvMazeClient.getShowById(id)).thenThrow(TvMazeResourceNotFoundException.class);
//
//        assertThrows(TvMazeShowNotFoundException.class, () -> tvMazeService.getShowById(id));
//    }
//
//    @Test
//    @DisplayName("Fetch Show should call TvMazeConfig's GetShowByIdURL method")
//    void fetchShow_ById_shouldCallTvMazeConfigGetShowByIdUrl() {
//        int id = 1;
//        TvMazeShowDto tvMazeShowDto = mock(TvMazeShowDto.class);
//        Show show = mock(Show.class);
//
//        when(tvMazeClient.getShowById(id)).thenReturn(tvMazeShowDto);
//
//        when(modelMapper.map(tvMazeShowDto, Show.class)).thenReturn(show);
//
//        tvMazeService.getShowById(id);
//
//        verify(tvMazeClient).getShowById(id);
//    }
//
//    @Test
//    void fetchSeason_ValidSeasonId_shouldReturnSeason() {
//        final int showId = 1;
//
//        List<TvMazeSeasonDto> seasons = Arrays.asList(new TvMazeSeasonDto(), new TvMazeSeasonDto(), new TvMazeSeasonDto());
//
//        when(tvMazeClient.getShowSeasons(showId)).thenReturn(seasons);
//        when(modelMapper.map(any(TvMazeSeasonDto.class), eq(Season.class))).thenReturn(new Season());
//
//        List<Season> seasonList = tvMazeService.getSeasons(showId);
//
//        assertThat(seasonList.size()).isEqualTo(3);
//    }
//
//
//    @Test
//    void fetchSeason_ValidSeasonId_shouldCallTvMazeClient() {
//        final int showId = 1;
//
//        List<TvMazeSeasonDto> seasons = Arrays.asList(new TvMazeSeasonDto(), new TvMazeSeasonDto(), new TvMazeSeasonDto());
//
//        when(tvMazeClient.getShowSeasons(showId)).thenReturn(seasons);
//        when(modelMapper.map(any(TvMazeSeasonDto.class), eq(Season.class))).thenReturn(new Season());
//
//        List<Season> seasonList = tvMazeService.getSeasons(showId);
//
//        verify(tvMazeClient).getShowSeasons(showId);
//    }
//
//    @Test
//    void fetchSeason_ValidSeasonId_shouldCallModelMapper() {
//        final int showId = 1;
//
//        List<TvMazeSeasonDto> seasons = Arrays.asList(new TvMazeSeasonDto(), new TvMazeSeasonDto(), new TvMazeSeasonDto());
//
//        when(tvMazeClient.getShowSeasons(showId)).thenReturn(seasons);
//        when(modelMapper.map(any(TvMazeSeasonDto.class), eq(Season.class))).thenReturn(new Season());
//
//        List<Season> seasonList = tvMazeService.getSeasons(showId);
//
//        verify(modelMapper, times(3)).map(any(TvMazeSeasonDto.class), eq(Season.class));
//    }
//
//    @Test
//    void fetchSeason_InvalidSeasonId_shouldThrowTvMazeShowInvalidIdException() {
//        int id = 1;
//        when(tvMazeClient.getShowSeasons(id)).thenThrow(TvMazeResourceNotFoundException.class);
//
//        assertThrows(TvMazeSeasonNotFoundException.class, () -> tvMazeService.getSeasons(id));
//    }
//

}
