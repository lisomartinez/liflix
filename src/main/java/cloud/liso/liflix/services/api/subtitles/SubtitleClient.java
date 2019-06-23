package cloud.liso.liflix.services.api.subtitles;

import cloud.liso.liflix.showservice.RequestSubtitleDto;

public interface SubtitleClient {
    SubtitleClient byImdb(String imdb);

    SubtitleClient byEpisode(int number);

    SubtitleClient byMovieByteSize(long size);

    SubtitleClient byMovieHash(String hash);

    SubtitleClient byQuery(String query);

    SubtitleClient bySeason(int season);

    SubtitleClient bySubLanguageId(String lang);

    SubtitleClient byTag(String tag);

    SubtitleClient from(RequestSubtitleDto req);

    String build();
}
