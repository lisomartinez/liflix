package cloud.liso.liflix.controllers.shows;

import cloud.liso.liflix.dto.SeasonDto;
import cloud.liso.liflix.exceptions.SeasonNotFoundException;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.services.show.SeasonService;
import cloud.liso.liflix.utils.ShowFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({SeasonController.class, SeasonService.class})
class SeasonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    //
//    @Autowired
//    private AccessTokenFactory tokenFactory;
//
    @MockBean
    private SeasonService seasonService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void getAllSeasons() {
    }

    @Test
    void getSeasonByShowIdAndSeasonNumber() {
    }

    @Test
    void getSeason_validShowIdAndValidSeasonNumber_ShouldReturnSeason() throws Exception {
//        String token = getToken();

        final int showId = 1;
        final int seasonNumber = 3;

        Show show = Mockito.mock(Show.class);
        Season lastSeasonWithEpisodes = ShowFactory.getLastSeasonWithEpisodes(show);
        SeasonDto seasonDto = modelMapper.map(lastSeasonWithEpisodes, SeasonDto.class);
        when(seasonService.getSeasonByNumber(showId, seasonNumber)).thenReturn(lastSeasonWithEpisodes);

        MvcResult result = this.mockMvc.perform(get(SeasonController.SHOWS + SeasonController.SHOW_ID
                + SeasonController.SEASONS + SeasonController.SEASON_NUMBER, showId, seasonNumber))
//                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        SeasonDto resultSeason = objectMapper.readValue(result.getResponse().getContentAsString(), SeasonDto.class);
        assertThat(resultSeason).isEqualTo(seasonDto);
    }

    @Test
    void getSeason_invalidShowId_ShouldThrowShowNotFoundException() throws Exception {
//        String token = getToken();

        final int showId = 0;
        final int seasonNumber = 3;

        when(seasonService.getSeasonByNumber(showId, seasonNumber)).thenThrow(new ShowNotFoundException());

        this.mockMvc.perform(get(SeasonController.SHOWS + SeasonController.SHOW_ID
                + SeasonController.SEASONS + SeasonController.SEASON_NUMBER, showId, seasonNumber))
//                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getSeason_validShowIdAndInvalidSeasonNumber_ShouldThrowSeasonNotFoundException() throws Exception {
//        String token = getToken();

        final int showId = 1;
        final int seasonNumber = 1;

        when(seasonService.getSeasonByNumber(showId, seasonNumber)).thenThrow(new SeasonNotFoundException());

        this.mockMvc.perform(get(SeasonController.SHOWS + SeasonController.SHOW_ID
                + SeasonController.SEASONS + SeasonController.SEASON_NUMBER, showId, seasonNumber))
//                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}