package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.exceptions.RequestParametersNotParsedException;
import cloud.liso.liflix.model.torrent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestParserTest {
    public static final String SHOW_ID = "1";
    public static final String SHOW_NAME = "Under the Dome";
    public static final String SEASON_NUMBER = "3";
    public static final String EPISODE_NUMBER = "13";

    private static Stream<Arguments> invalidIntParamsType() {
        return Stream.of(
                Arguments.of("invalid", SHOW_NAME, SEASON_NUMBER, EPISODE_NUMBER),
                Arguments.of(SHOW_ID, SHOW_NAME, "invalid", EPISODE_NUMBER),
                Arguments.of(SHOW_ID, SHOW_NAME, SEASON_NUMBER, "invalid"));
    }

    private static Stream<Arguments> withoutParams() {
        return Stream.of(
                Arguments.of(withoutShowId()),
                Arguments.of(withoutShowName()),
                Arguments.of(withoutSeasonNumber()),
                Arguments.of(withoutEpisodeNumber()),
                Arguments.of(emptyParams())
        );
    }

    private static Map<String, String> emptyParams() {
        return new HashMap<>();
    }

    private static Map<String, String> withoutShowName() {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_ID, SHOW_ID);
        params.put(RequestParser.SEASON_NUMBER, SEASON_NUMBER);
        params.put(RequestParser.EPISODE_NUMBER, EPISODE_NUMBER);
        return params;
    }

    private static Map<String, String> withoutSeasonNumber() {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_ID, SHOW_ID);
        params.put(RequestParser.SHOW_NAME, SHOW_NAME);
        params.put(RequestParser.EPISODE_NUMBER, EPISODE_NUMBER);
        return params;
    }

    private static Map<String, String> withoutEpisodeNumber() {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_ID, SHOW_ID);
        params.put(RequestParser.SHOW_NAME, SHOW_NAME);
        params.put(RequestParser.SEASON_NUMBER, SEASON_NUMBER);
        return params;
    }

    private static Map<String, String> withoutShowId() {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_NAME, SHOW_NAME);
        params.put(RequestParser.SEASON_NUMBER, SEASON_NUMBER);
        params.put(RequestParser.EPISODE_NUMBER, EPISODE_NUMBER);
        return params;
    }

    @Test
    void from_paramsMap_returnsRequestInstance() {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_ID, SHOW_ID);
        params.put(RequestParser.SHOW_NAME, SHOW_NAME);
        params.put(RequestParser.SEASON_NUMBER, SEASON_NUMBER);
        params.put(RequestParser.EPISODE_NUMBER, EPISODE_NUMBER);

        Request expected = Request.builder()
                .showId(1)
                .showName("Under the Dome")
                .seasonNumber(3)
                .episodeNumber(13)
                .build();

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.from(params);
        assertThat(request).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("invalidIntParamsType")
    void from_invalidIntParamsType_throwRequestParametersNotParsedException(String showId, String showName, String seasonNumber, String episodeNumber) {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_ID, showId);
        params.put(RequestParser.SHOW_NAME, showName);
        params.put(RequestParser.SEASON_NUMBER, seasonNumber);
        params.put(RequestParser.EPISODE_NUMBER, episodeNumber);
        assertThrows(RequestParametersNotParsedException.class, () -> new RequestParser().from(params));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void from_emptyShowName_throwsRequestParametersNotParsedException(String showName) {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParser.SHOW_ID, SHOW_ID);
        params.put(RequestParser.SHOW_NAME, showName);
        params.put(RequestParser.SEASON_NUMBER, SEASON_NUMBER);
        params.put(RequestParser.EPISODE_NUMBER, EPISODE_NUMBER);
        assertThrows(RequestParametersNotParsedException.class, () -> new RequestParser().from(params));
    }

    @ParameterizedTest
    @MethodSource("withoutParams")
    void from_WithoutParams_throwRequestParametersNotParsedException(Map<String, String> params) {
        assertThrows(RequestParametersNotParsedException.class, () -> new RequestParser().from(params));
    }
}