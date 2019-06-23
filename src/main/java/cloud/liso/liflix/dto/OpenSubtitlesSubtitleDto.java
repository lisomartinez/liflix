package cloud.liso.liflix.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenSubtitlesSubtitleDto implements SubtitleDto {
    @JsonProperty("SubFileName")
    private String subFileName;

    @JsonProperty("SubDownloadLink")
    private String downloadLink;
}
