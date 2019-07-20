package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMDocument;

import java.util.List;

public interface EpisodeParser {
    List<Torrent> parseDOMDocument(DOMDocument document);
}
