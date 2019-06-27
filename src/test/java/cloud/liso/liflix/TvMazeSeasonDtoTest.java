package cloud.liso.liflix;

import cloud.liso.liflix.dto.TvMazeSeasonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.json.JacksonTester;

public class TvMazeSeasonDtoTest {
    private JacksonTester<TvMazeSeasonDto> json;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

//    @Test
//    void parseJson_ValidJson_ShouldReturnSeasonInstance() throws IOException {
//        TvMazeSeasonDto expected = getSeason();
//        TvMazeSeasonDto season = json.readObject(JsonTestFiles.getSeasonOne());
//    }
//
//    private TvMazeSeasonDto getSeason() {
//        return TvMazeSeasonDto.builder()
//                .id(1)
//                .url("http://www.tvmaze.com/seasons/1/under-the-dome-season-1")
//                .name("N/A")
//                .episodes(13)
//                .premiereDate(LocalDate.of(2013, 6, 24))
//                .endDate(LocalDate.of(2013, 9, 16))
//                .image("http://static.tvmaze.com/uploads/images/original_untouched/24/60941.jpg")
//                .summary("N/A")
//                .build();
//
//    }
}
