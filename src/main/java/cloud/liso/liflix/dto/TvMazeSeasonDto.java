package cloud.liso.liflix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TvMazeSeasonDto {

    private final static Pattern pattern = Pattern.compile("(<.+?>)");

    private int id;
    private int number;
    private String name;
    private int episodeOrder;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiereDate = LocalDate.of(1900, 1, 1);

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate = LocalDate.of(1900, 1, 1);

    private String imageUrl;
    private String tvMazeUrl;
    private String summary;

    @JsonProperty("name")
    private void setNameOrDefault(String name) {
        this.name = getOrDefault(name);
    }

    private String getOrDefault(String field) {
        return field == null || field.isEmpty() ? "N/A" : field;
    }

    @JsonSetter(value = "summary")
    private void setSummaryJson(String summaryJson) {
        if (summaryJson == null || summaryJson.isEmpty()) {
            this.summary = "N/A";
        } else {
            Matcher matcher = pattern.matcher(summaryJson);
            this.summary = matcher.replaceAll("");
        }
    }

    @JsonProperty("url")
    private void setUrlOrDefault(String url) {
        this.tvMazeUrl = getOrDefault(url);
    }

    @JsonProperty(value = "image")
    private void originalImage(Map<String, String> image) {

        if (image == null || image.isEmpty()) {
            this.imageUrl = "N/A";
        } else {
            this.imageUrl = image.putIfAbsent("original", "N/A");
        }
    }

}
