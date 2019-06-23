package cloud.liso.liflix.services.api.subtitles;

import cloud.liso.liflix.dto.SubtitleDto;
import cloud.liso.liflix.showservice.RequestSubtitleDto;

import java.util.List;

public interface SubtitleDownloader {
    List<? extends SubtitleDto> getList(RequestSubtitleDto req);
}
