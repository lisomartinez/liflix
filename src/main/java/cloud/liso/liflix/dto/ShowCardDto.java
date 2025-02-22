package cloud.liso.liflix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowCardDto {
    private int id;
    private String name;
    private String image;
    private double rating;
    private long seasons;
}
