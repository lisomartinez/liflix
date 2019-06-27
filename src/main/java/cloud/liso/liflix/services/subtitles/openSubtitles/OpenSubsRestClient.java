package cloud.liso.liflix.services.subtitles.openSubtitles;

import cloud.liso.liflix.services.subtitles.RequestSubtitleDto;
import cloud.liso.liflix.services.subtitles.SubtitleClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<Field> url;
    private OpenSubsFields fields;

    @Autowired
    public OpenSubsRestClient(OpenSubsFields fields) {
        this.fields = fields;
        this.url = new ArrayList<>();
        url.add(fields.createBaseUrl());
    }

    @Override
    public SubtitleClient byEpisode(int number) {
        if (number == 0) return this;
        url.add(fields.createEpisode(number));
        return this;
    }

    @Override
    public SubtitleClient byImdb(@NotNull String imdb) {
        if (imdb.isEmpty()) return this;
        url.add(fields.createImdb(imdb));
        return this;
    }

    @Override
    public SubtitleClient byMovieByteSize(long size) {
        if (size == 0) return this;
        url.add(fields.createMovieSize(size));
        return this;
    }

    @Override
    public SubtitleClient byMovieHash(@NotNull String hash) {
        if (hash.isEmpty()) return this;
        if (hash.length() != 16) {

        }
        url.add(fields.createMovieHash(hash));
        return this;
    }

    @Override
    public SubtitleClient byQuery(@NotNull String query) {
        if (query.isEmpty()) return this;
        String q = getEncode(query);
        url.add(fields.createQuery(q));
        return this;
    }

    private String getEncode(@NotNull String query) {
        try {
            return URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public SubtitleClient bySeason(int season) {
        if (season == 0) return this;
        url.add(fields.createSeason(season));
        return this;
    }

    @Override
    public SubtitleClient bySubLanguageId(@NotNull String lang) {
        if (lang.isEmpty()) return this;
        url.add(fields.createLanguage(lang));
        return this;
    }

    @Override
    public SubtitleClient byTag(@NotNull String tag) {
        if (tag.isEmpty()) return this;
        String q = getEncode(tag);
        url.add(fields.createTag(tag));
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
