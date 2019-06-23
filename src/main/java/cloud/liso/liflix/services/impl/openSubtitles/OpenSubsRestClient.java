package cloud.liso.liflix.services.impl.openSubtitles;

import cloud.liso.liflix.services.api.subtitles.SubtitleClient;
import cloud.liso.liflix.showservice.RequestSubtitleDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Component
@Scope(value = "prototype")
public class OpenSubsRestClient implements SubtitleClient {
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
    private static final String EPISODE_PREFIX = "/episode-";
    private static final String IMDB_PREFIX = "/imdbid-";
    private static final String MOVIE_BYTE_SIZE_PREFIX = "/moviebytesize-";
    private static final String MOVIE_HASH_PREFIX = "/moviehash-";
    private static final String QUERY_PREFIX = "/query-";
    private static final String SEASON_PREFIX = "/season-";
    private static final String LANGUAGE_PREFIX = "/sublanguageid-";
    private static final String TAG_PREFIX = "/tag-";

    private List<Field> url;

    public OpenSubsRestClient() {
        this.url = new ArrayList<>();
        url.add(Field.of(BASE_URL_POSITION, BASE_URL));
    }

    @Override
    public SubtitleClient byEpisode(int number) {
        if (number == 0) return this;
        url.add(Field.of(EPISODE_POSITION, EPISODE_PREFIX + number));
        return this;
    }

    @Override
    public SubtitleClient byImdb(String imdb) {
        if (imdb == null || imdb.isEmpty()) return this;
        url.add(Field.of(IMDB_POSITION, IMDB_PREFIX + imdb));
        return this;
    }

    @Override
    public SubtitleClient byMovieByteSize(long size) {
        if (size == 0) return this;
        url.add(Field.of(MOVIE_BYTE_SIZE_POSITION, MOVIE_BYTE_SIZE_PREFIX + size));
        return this;
    }

    @Override
    public SubtitleClient byMovieHash(String hash) {
        if (hash == null || hash.isEmpty()) return this;
        if (hash.length() != 16) {

        }
        url.add(Field.of(MOVIE_HASH_POSITION, MOVIE_HASH_PREFIX + hash));
        return this;
    }

    @Override
    public SubtitleClient byQuery(String query) {
        if (query == null || query.isEmpty()) return this;
        String q = getEncode(query);
        url.add(Field.of(QUERY_POSITION, QUERY_PREFIX + q));

        return this;
    }

    private String getEncode(String query) {
        try {
            return URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public SubtitleClient bySeason(int season) {
        url.add(Field.of(SEASON_POSITION, SEASON_PREFIX + season));
        return this;
    }

    @Override
    public SubtitleClient bySubLanguageId(String lang) {
        if (lang == null || lang.isEmpty()) return this;
        url.add(Field.of(LANGUAGE_POSITION, LANGUAGE_PREFIX + lang));
        return this;
    }

    @Override
    public SubtitleClient byTag(String tag) {
        if (tag == null || tag.isEmpty()) return this;
        String q = getEncode(tag);
        url.add(Field.of(TAG_POSITION, TAG_PREFIX + q));

        return this;
    }

    @Override
    public SubtitleClient from(RequestSubtitleDto req) {
        return this.byEpisode(req.getEpisode())
                .byImdb(req.getImdb())
                .byMovieByteSize(req.getSize())
                .byMovieHash(req.getHash())
                .bySeason(req.getSeason())
                .byQuery(req.getQuery())
                .bySubLanguageId(req.getLanguage())
                .byTag(req.getTag());
    }

    @Override
    public String build() {
        return url.stream()
                .sorted(comparing(Field::getPosition))
                .map(Field::toString)
                .collect(Collectors.joining(""));
    }
}
