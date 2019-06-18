package cloud.liso.liflix.controllers.shows;

import cloud.liso.liflix.dto.SeasonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SeasonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SeasonController seasonController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getSeasonByShowId_validShowIdAndValidSeasonId_ReturnsStatusOkWithSeasonDto() throws Exception {
        MvcResult result = mockMvc.perform(get(SeasonController.SHOWS + SeasonController.SHOW_ID + SeasonController.SEASON + SeasonController.SEASON_NUMBER, 1, 3))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        SeasonDto seasonDto = objectMapper.readValue(contentAsString, SeasonDto.class);
        assertThat(seasonDto.getShowId()).isEqualTo(1);
        assertThat(seasonDto.getNumber()).isEqualTo(3);
        assertThat(seasonDto.getEpisodes().isEmpty()).isFalse();
    }
}
