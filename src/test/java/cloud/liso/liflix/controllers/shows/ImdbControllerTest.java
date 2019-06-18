package cloud.liso.liflix.controllers.shows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ImdbControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ImdbController imdbController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getImdbUrl() throws Exception {
        mvc.perform(get(ImdbController.IMDB + ImdbController.ID, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is("https://www.imdb.com/title/tt1553656/videoplayer/vi3897206297")));
    }
}