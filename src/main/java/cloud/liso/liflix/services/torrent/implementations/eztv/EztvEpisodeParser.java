package cloud.liso.liflix.services.torrent.implementations.eztv;

import cloud.liso.liflix.exceptions.EpisodeParsingException;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMDocument;
import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.http_client.DocumentSelector;
import cloud.liso.liflix.services.torrent.parsing.EpisodeParser;
import cloud.liso.liflix.services.torrent.parsing.TorrentElementsFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EztvEpisodeParser implements EpisodeParser {

    private DocumentSelector documentSelector;
    private TorrentElementsFactory torrentsElementsFactory;

    @Autowired
    public EztvEpisodeParser(DocumentSelector documentSelector, @Qualifier("eztvTorrentElementsFactory") TorrentElementsFactory torrentsElementsFactory) {
        this.documentSelector = documentSelector;
        this.torrentsElementsFactory = torrentsElementsFactory;
    }

    @Override
    public List<Torrent> parseDOMDocument(@NotNull DOMDocument document) {
        try {
            List<DOMElement> elements = documentSelector.apply(document);
            return elements.stream().map(this::parse).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new EpisodeParsingException(ex.getMessage());
        }
    }

    private Torrent parse(DOMElement element) {
        return torrentsElementsFactory.createTorrentElements(element, Torrent.builder()).build();
    }
}
