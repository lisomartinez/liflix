package cloud.liso.liflix.services.subtitles.open_subtitles;

import cloud.liso.liflix.exceptions.NullRequestSubtitleDtoException;
import cloud.liso.liflix.services.subtitles.RequestSubtitleDto;
import cloud.liso.liflix.services.subtitles.SubtitleClient;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(value= BeanDefinition.SCOPE_PROTOTYPE, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class OpenSubsRestClient implements SubtitleClient {
    private List<Field> url;
    private OpenSubsFields openSubsFields;

    @Autowired
    public OpenSubsRestClient(OpenSubsFields openSubsFields) {
        this.openSubsFields = openSubsFields;
        this.url = new ArrayList<>();
        url.add(openSubsFields.createBaseUrl());
    }

    @Override
    public SubtitleClient byEpisode(int number) {
        if (number == 0) return this;
        url.add(openSubsFields.createEpisode(number));
        return this;
    }

    @Override
    public SubtitleClient byImdb(@NotNull String imdb) {
        if (StringUtils.isBlank(imdb)) return this;
        url.add(openSubsFields.createImdb(imdb));
        return this;
    }

    @Override
    public SubtitleClient byMovieByteSize(long size) {
        if (size == 0) return this;
        url.add(openSubsFields.createMovieSize(size));
        return this;
    }

    @Override
    public SubtitleClient byMovieHash(@NotNull String hash) {
        if (StringUtils.isBlank(hash)) return this;
        if (hash.length() != 16) {

        }
        url.add(openSubsFields.createMovieHash(hash));
        return this;
    }

    @Override
    public SubtitleClient byQuery(@NotNull String query) {
        if (StringUtils.isBlank(query)) return this;
        String q = getEncode(query);
        url.add(openSubsFields.createQuery(q));
        return this;
    }

    private String getEncode(@NotNull String query) {
        if (StringUtils.isBlank(query)) return "";
        try {
            return URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    @Override
    public SubtitleClient bySeason(int season) {
        if (season == 0) return this;
        url.add(openSubsFields.createSeason(season));
        return this;
    }

    @Override
    public SubtitleClient bySubLanguageId(@NotNull String lang) {
        if (StringUtils.isBlank(lang)) return this;
        url.add(openSubsFields.createLanguage(lang));
        return this;
    }

    @Override
    public SubtitleClient byTag(@NotNull String tag) {
        if (StringUtils.isBlank(tag)) return this;
        String q = getEncode(tag);
        url.add(openSubsFields.createTag(q));
        return this;
    }

    @Override
    public SubtitleClient from(RequestSubtitleDto req) {
        if (req == null) throw new NullRequestSubtitleDtoException();

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
