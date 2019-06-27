package cloud.liso.liflix.services.subtitles;

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

    public static RequestSubtitleDto createEmpty() {
        return new RequestSubtitleDto("", 0L, 0, 0, "", "", "", "");
    }
}
