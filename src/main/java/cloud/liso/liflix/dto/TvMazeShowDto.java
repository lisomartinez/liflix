package cloud.liso.liflix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TvMazeShowDto {

    private final static Pattern pattern = Pattern.compile("(<.+?>)");

    private static final String DEFAULT = "N/A";

    private int id;
    private String name;
    private String type;
    private String language;
    private List<GenreDto> genres;
    private String status;
    private int runtime = 0;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiered = LocalDate.of(1900, 1, 1);

    private String officialSite;
    private TvMazeScheduleDto schedule;
    private String tvMazeUrl;
    private String imdbUrl;
    private String imageUrl;
    private String summary;

    private LocalDateTime updateDateTime;

    @JsonProperty("type")
    private void setTypeOrDefault(String type) {
        this.type = getOrDefault(type);
    }

    private String getOrDefault(String field) {
        return field == null ? "N/A" : field;
    }

    @JsonProperty("language")
    private void setLanguageOrDefault(String language) {
        this.language = getOrDefault(language);
    }

    @JsonProperty("status")
    private void setStatusOrDefault(String status) {
        this.status = getOrDefault(status);
    }

    @JsonSetter("genres")
    private void setGenresList(List<String> genres) {
        if (genres.isEmpty()) {
            this.genres = new ArrayList<>(Arrays.asList(GenreDto.of("N/A")));
        } else {
            this.genres = genres.stream().map(GenreDto::of).collect(Collectors.toList());
        }
    }

    @JsonProperty("officialSite")
    private void setOfficialSiteOrDefault(String officialSite) {
        this.officialSite = getOrDefault(officialSite);
    }

    @JsonProperty("url")
    private void setTvMazeUrl(String url) {
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

    @JsonProperty("externals")
    private void imdbUrl(Map<String, String> externals) {
        String imdb = externals.getOrDefault("imdb", "N/A");
        if (!imdb.equals("N/A")) {
            this.imdbUrl = "https://www.imdb.com/title/" + imdb;
        } else {
            this.imdbUrl = imdb;
        }
    }

    @JsonProperty(value = "summary")
    private void setSummaryJson(String summaryJson) {
        if (summaryJson == null || summaryJson.isEmpty()) {
            this.summary = "N/A";
        } else {
            Matcher matcher = pattern.matcher(summaryJson);
            this.summary = matcher.replaceAll("");
        }
    }
}
