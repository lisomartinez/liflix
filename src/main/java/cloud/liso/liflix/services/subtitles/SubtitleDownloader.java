package cloud.liso.liflix.services.subtitles;

import cloud.liso.liflix.dto.OpenSubtitlesSubtitleDto;

import java.util.List;

public interface SubtitleDownloader {
    List<OpenSubtitlesSubtitleDto> getList(RequestSubtitleDto req);
}
