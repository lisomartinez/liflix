package cloud.liso.liflix.services.subtitles.openSubtitles;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OpenSubsFields {
    private static final int BASE_URL_POSITION = 0;
    private static final int EPISODE_POSITION = 1;
    private static final int IMDB_POSITION = 2;
    private static final int MOVIE_BYTE_SIZE_POSITION = 3;
    private static final int MOVIE_HASH_POSITION = 4;
    private static final int QUERY_POSITION = 5;
    private static final int SEASON_POSITION = 6;
    private static final int LANGUAGE_POSITION = 7;
    private static final int TAG_POSITION = 8;

    private static final String BASE_URL = "https://rest.opensubtitles.org/search";
    private static final String EPISODE_PREFIX = "/parsingUtils-";
    private static final String IMDB_PREFIX = "/imdbid-";
    private static final String MOVIE_BYTE_SIZE_PREFIX = "/moviebytesize-";
    private static final String MOVIE_HASH_PREFIX = "/moviehash-";
    private static final String QUERY_PREFIX = "/query-";
    private static final String SEASON_PREFIX = "/season-";
    private static final String LANGUAGE_PREFIX = "/sublanguageid-";
    private static final String TAG_PREFIX = "/tag-";

    Field createBaseUrl() {
        return Field.of(BASE_URL_POSITION, BASE_URL);
    }

    Field createEpisode(int number) {
        return Field.of(EPISODE_POSITION, EPISODE_PREFIX + number);
    }

    Field createImdb(@NotNull String imdb) {
        return Field.of(IMDB_POSITION, IMDB_PREFIX + imdb);
    }

    Field createMovieSize(long size) {
        return Field.of(MOVIE_BYTE_SIZE_POSITION, MOVIE_BYTE_SIZE_PREFIX + size);
    }

    Field createMovieHash(@NotNull String hash) {
        return Field.of(MOVIE_HASH_POSITION, MOVIE_HASH_PREFIX + hash);
    }

    Field createQuery(@NotNull String query) {
        return Field.of(QUERY_POSITION, QUERY_PREFIX + query);
    }

    Field createSeason(int season) {
        return Field.of(SEASON_POSITION, SEASON_PREFIX + season);
    }

    Field createLanguage(@NotNull String lang) {
        return Field.of(LANGUAGE_POSITION, LANGUAGE_PREFIX + lang);
    }

    Field createTag(@NotNull String tag) {
        return Field.of(TAG_POSITION, TAG_PREFIX + tag);
    }
}
