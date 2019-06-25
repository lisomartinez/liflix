package cloud.liso.liflix.services.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSubtitleDto {
    private String hash;
    private long size;
    private int season;
    private int episode;
    private String imdb;
    private String language;
    private String tag;
    private String query;
}
