package cloud.liso.liflix.services.subtitles;

import cloud.liso.liflix.dto.SubtitleDto;
import cloud.liso.liflix.services.show.RequestSubtitleDto;

import java.util.List;

public interface SubtitleDownloader {
    List<? extends SubtitleDto> getList(RequestSubtitleDto req);
}
