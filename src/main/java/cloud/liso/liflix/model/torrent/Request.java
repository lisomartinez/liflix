package cloud.liso.liflix.model.torrent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private final int showId;
    private final String showName;
    private final int seasonNumber;
    private final int episodeNumber;
}
