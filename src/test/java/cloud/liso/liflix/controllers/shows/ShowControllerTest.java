package cloud.liso.liflix.controllers.shows;

import cloud.liso.liflix.dto.GenreDto;
import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.dto.ShowDto;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.model.show.ShowCard;
import cloud.liso.liflix.services.show.ShowService;
import cloud.liso.liflix.showservice.utils.ShowFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest({ShowController.class, ShowService.class})
class ShowControllerTest {

    //
//    @MockBean
//    private UserRepository userRepository;
//
    @Autowired
    private MockMvc mockMvc;
    //
//    @Autowired
//    private AccessTokenFactory tokenFactory;
//
    @MockBean
    private ShowService showService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void getShow_ValidShowId_ShouldReturnShow() throws Exception {
//        String token = getToken();

        Show show = ShowFactory.getShowComplete(LocalDateTime.now().withSecond(0).withNano(0));
        ShowDto showDto = modelMapper.map(show, ShowDto.class);
        when(showService.getShowById(anyInt())).thenReturn(show);
        MvcResult result = this.mockMvc.perform(get(ShowController.SHOWS + ShowController.SHOW_ID, show.getId()))
//                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        ShowDto resultShow = objectMapper.readValue(result.getResponse().getContentAsString(), ShowDto.class);

        assertThat(resultShow).isEqualTo(showDto);
    }

    //
//    private String getToken() throws Exception {
//        return tokenFactory.createValidTokenMock(userRepository, mockMvc);
//    }
//
    @Test
    void getShow_InvalidShowIßd_ShouldThrowShowNotFoundException() throws Exception {
//        String token = getToken();

        when(showService.getShowById(anyInt())).thenThrow(new ShowNotFoundException());

        this.mockMvc.perform(get(ShowController.SHOWS + ShowController.SHOW_ID, 0))
//                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getShowPage_validPageNumberAndValidSize() throws Exception {
        Show show = ShowFactory.getShowComplete(LocalDateTime.now().withSecond(0).withNano(0));
        ShowDto expected = modelMapper.map(show, ShowDto.class);
        List<Show> shows = new ArrayList<>(Arrays.asList(show));
        final int p = 0;
        final int s = 10;

        Page<Show> page = new PageImpl<>(shows, PageRequest.of(p, s), shows.size());
        when(showService.getShowPage(anyInt(), anyInt())).thenReturn(page);
        MvcResult result = this.mockMvc
                .perform(get(ShowController.SHOWS)
                        .param("page", String.valueOf(p))
                        .param("size", String.valueOf(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andDo(print())
                .andReturn();
        ObjectNode jsonNodes = objectMapper.readValue(result.getResponse().getContentAsString(), ObjectNode.class);
        JsonNode showStr = jsonNodes.get("content").get(0);
        ShowDto res = objectMapper.convertValue(showStr, ShowDto.class);
        assertThat(res).isEqualTo(expected);
    }

    @Test
    void getShowPage_emptyPage() throws Exception {
        List<Show> shows = new ArrayList<>();
        final int p = 0;
        final int s = 10;

        Page<Show> page = new PageImpl<>(shows, PageRequest.of(p, s), shows.size());
        when(showService.getShowPage(anyInt(), anyInt())).thenReturn(page);
        this.mockMvc
                .perform(get(ShowController.SHOWS)
                        .param("page", String.valueOf(p))
                        .param("size", String.valueOf(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andDo(print());
    }


    @Test
    void getIndexPage_withContent_ReturnsPageWithContent() throws Exception {

        String genre = "Drama";
        ShowCardDto showCard = ShowCardDto.builder()
                .id(1)
                .name("test")
                .rating(5.4)
                .image("Fake Image")
                .seasons(2L)
                .build();

        List<ShowCardDto> showCards = new ArrayList<>(Arrays.asList(showCard));
        final int p = 0;
        final int s = 10;
        Page<ShowCardDto> page = new PageImpl<>(showCards, PageRequest.of(p, s), showCards.size());

        when(showService.getPageByGenre(anyString(), anyInt(), anyInt())).thenReturn(page);
        MvcResult result = this.mockMvc
                .perform(get(ShowController.SHOWS + ShowController.GENRES + ShowController.NAME, showCard.getId())
                        .param("page", String.valueOf(p))
                        .param("size", String.valueOf(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andDo(print())
                .andReturn();
        ObjectNode jsonNodes = objectMapper.readValue(result.getResponse().getContentAsString(), ObjectNode.class);
        JsonNode showStr = jsonNodes.get("content").get(0);
        ShowCardDto res = objectMapper.convertValue(showStr, ShowCardDto.class);
        assertThat(res).isEqualTo(showCard);
    }

    @Test
    void getPageByGenre_empty_ReturnsEmptyPage() throws Exception {

        List<ShowCardDto> showCards = new ArrayList<>();
        final int id = 0;
        final int p = 0;
        final int s = 10;
        Page<ShowCardDto> page = new PageImpl<>(showCards, PageRequest.of(p, s), showCards.size());


        when(showService.getPageByGenre(anyString(), anyInt(), anyInt())).thenReturn(page);
        this.mockMvc
                .perform(get(ShowController.SHOWS + ShowController.GENRES + ShowController.NAME, id)
                        .param("page", String.valueOf(p))
                        .param("size", String.valueOf(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andDo(print());
    }

    @Test
    void getIndexPage() throws Exception {
//        GenreDto genre = GenreDto.of("Drama");
        final String genre = "Drama";
        ShowCard showCard = ShowCard.builder()
                .id("1")
                .name("test")
                .rating(5.4)
                .image("Fake Image")
                .seasons(2L)
                .genre(genre)
                .build();

        List<ShowCard> showCards = new ArrayList<>(Arrays.asList(showCard));
        Map<String, List<ShowCard>> index = new HashMap<>();
        index.put(genre, showCards);

        List<ShowCardDto> showCardDtos = showCards.stream().map(sh -> modelMapper.map(sh, ShowCardDto.class)).collect(Collectors.toList());
        GenreDto genreDto = GenreDto.of(genre);
        Map<GenreDto, List<ShowCardDto>> expected = new HashMap<>();
        expected.put(genreDto, showCardDtos);
        String expectedJson = objectMapper.writeValueAsString(expected);

        when(showService.getIndex()).thenReturn(index);
        MvcResult result = this.mockMvc.perform(get(ShowController.SHOWS + ShowController.INDEX))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();
        assertThat(resultJson).isEqualTo(expectedJson);
    }

//    @Test
//    void getSeason_validShowIdAndValidSeasonNumber_ShouldReturnSeason() throws Exception {
//        String token = getToken();
//
//        final int showId = 1;
//        final int seasonNumber = 3;
//
//        Season lastSeasonWithEpisodes = ShowFactory.getLastSeasonWithEpisodes();
//        SeasonDto seasonDto = modelMapper.map(lastSeasonWithEpisodes, SeasonDto.class);
//        when(showService.getSeasonById(showId, seasonNumber)).thenReturn(lastSeasonWithEpisodes);
//
//        MvcResult result = this.mockMvc.perform(get(ShowController.SHOWS + ShowController.SHOW_ID +
//                ShowController.SEASON + ShowController.SEASON_NUMBER, showId, seasonNumber)
//                .header("Authorization", "Bearer " + token))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//
//        SeasonDto resultSeason = objectMapper.readValue(result.getResponse().getContentAsString(), SeasonDto.class);
//        assertThat(resultSeason).isEqualTo(seasonDto);
//    }
//
//    @Test
//    void getSeason_invalidShowId_ShouldThrowShowNotFoundException() throws Exception {
//        String token = getToken();
//
//        final int showId = 0;
//        final int seasonNumber = 3;
//
//        when(showService.getSeasonById(showId, seasonNumber)).thenThrow(new ShowNotFoundException());
//
//        this.mockMvc.perform(get(ShowController.SHOWS + ShowController.SHOW_ID +
//                ShowController.SEASON + ShowController.SEASON_NUMBER, showId, seasonNumber)
//                .header("Authorization", "Bearer " + token))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getSeason_validShowIdAndInvalidSeasonNumber_ShouldThrowSeasonNotFoundException() throws Exception {
//        String token = getToken();
//
//        final int showId = 1;
//        final int seasonNumber = 1;
//
//        when(showService.getSeasonById(showId, seasonNumber)).thenThrow(new SeasonNotFoundException());
//
//        this.mockMvc.perform(get(ShowController.SHOWS + ShowController.SHOW_ID +
//                ShowController.SEASON + ShowController.SEASON_NUMBER, showId, seasonNumber)
//                .header("Authorization", "Bearer " + token))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getEpisode_ValidShowAndValidSeasonAndValidEpisode_ShouldReturnEpisode() {
////        String token = getToken();
////
////        final int showId = 1;
////        final int seasonNumber = 1;
////        final int episodeNumber = 1;
////
////        when(showService.getLastEpisodeFromLastSeason(showId, seasonNumber, episodeNumber)).thenReturn()
//    }
}
