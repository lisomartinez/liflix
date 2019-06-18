package cloud.liso.liflix.services.api.torrent;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.api.httpClient.DOMDocument;

import java.util.List;

public interface EpisodeParser {
    List<Torrent> parseDOMDocument(DOMDocument document);
}
