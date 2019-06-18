package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowCard {
    private String id;
    private String name;
    private String image;
    private double rating;
    private long seasons;
    private String genre;
}
