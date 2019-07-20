package cloud.liso.liflix.controllers.torrents;

import cloud.liso.liflix.dto.TorrentDto;
import cloud.liso.liflix.services.torrent.parsing.RequestParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TorrentsControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TorrentsController torrentsController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllTorrents_WithDefaultSortPolicy_ShouldReturnStatusOkAndListOfTorrentsDto() throws Exception {
        MvcResult result = mockMvc.perform(get(TorrentsController.TORRENTS + TorrentsController.ALL_TORRENTS)
                .param(RequestParser.SHOW_ID, "1")
                .param(RequestParser.SHOW_NAME, "Under the Dome")
                .param(RequestParser.SEASON_NUMBER, "1")
                .param(RequestParser.EPISODE_NUMBER, "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        List<TorrentDto> torrents = objectMapper.readValue(content, new TypeReference<List<TorrentDto>>() {
        });
        assertThat(torrents).isNotEmpty();
    }


}