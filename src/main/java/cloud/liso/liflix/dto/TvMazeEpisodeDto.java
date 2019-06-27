package cloud.liso.liflix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TvMazeEpisodeDto {

    private static final Pattern pattern = Pattern.compile("(<.+?>)");

    private int id;

    private String name = "N/A";

    private int number = 0;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Builder.Default
    private LocalDate airdate = LocalDate.of(1900, 1, 1);

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime airtime = LocalTime.of(0, 0);
    private Integer runtime = 0;
    private String imageUrl;
    private String tvmazeUrl;
    private String summary;
    private int season;

    @JsonProperty("url")
    private void setUrlOrDefault(String url) {
        this.tvmazeUrl = getOrDefault(url);
    }

    private String getOrDefault(String field) {
        return field == null ? "N/A" : field;
    }

    @JsonProperty(value = "image")
    private void originalImage(Map<String, String> image) {

        if (image == null || image.isEmpty()) {
            this.imageUrl = "N/A";
        } else {
            this.imageUrl = image.putIfAbsent("original", "N/A");
        }
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
}
