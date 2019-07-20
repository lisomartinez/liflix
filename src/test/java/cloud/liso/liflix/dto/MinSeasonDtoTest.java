package cloud.liso.liflix.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MinSeasonDtoTest {

    private ObjectMapper objectMapper;

    @Test
    void MinSeasonDto_testDeserialize_shouldReturnInstance() throws IOException {
        objectMapper = new ObjectMapper();
        MinSeasonDto expected = MinSeasonDto.builder()
                .id(1)
                .number(1)
                .episodeOrder(13)
                .premiereDate(LocalDate.of(2010, 9, 12))
                .endDate(LocalDate.of(2010, 9, 12))
                .image("image")
                .tvMaze("tvMaze")
                .summary("summary")
                .build();
        String minSeasonDto = "{\"id\": 1, \"number\": 1, \"episodeOrder\": 13, \"premiereDate\": \"2010-09-12\", \"endDate\": \"2010-09-12\", \"image\": \"image\", \"tvMaze\": \"tvMaze\", \"summary\": \"summary\"  }";
        MinSeasonDto result = objectMapper.readValue(minSeasonDto, MinSeasonDto.class);
        assertThat(result).isEqualTo(expected);
    }
}