package cloud.liso.liflix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeasonDto {
    private int id;
    private int showId;
    private int number;
    private List<EpisodeDto> episodes;
}
