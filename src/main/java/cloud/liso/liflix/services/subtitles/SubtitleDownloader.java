package cloud.liso.liflix.services.subtitles;

import cloud.liso.liflix.dto.SubtitleDto;

import java.util.List;

public interface SubtitleDownloader {
    List<? extends SubtitleDto> getList(RequestSubtitleDto req);
}
