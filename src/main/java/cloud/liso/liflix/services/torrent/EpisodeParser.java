package cloud.liso.liflix.services.torrent;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.httpClient.DOMDocument;

import java.util.List;

public interface EpisodeParser {
    List<Torrent> parseDOMDocument(DOMDocument document);
}
