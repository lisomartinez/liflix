package cloud.liso.liflix.services.torrent.implementations.zooqle;

import cloud.liso.liflix.exceptions.EpisodeParsingException;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMDocument;
import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.torrent.implementations.zooqle.selectors.element.ZooqleDocumentSelector;
import cloud.liso.liflix.services.torrent.parsing.EpisodeParser;
import cloud.liso.liflix.services.torrent.parsing.TorrentElementsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooqleEpisodeParser implements EpisodeParser {

    private ZooqleDocumentSelector documentSelector;
    private TorrentElementsFactory torrentsElementsFactory;

    @Autowired
    public ZooqleEpisodeParser(ZooqleDocumentSelector documentSelector, @Qualifier("zooqleTorrentElementsFactory") TorrentElementsFactory torrentsElementsFactory) {
        this.documentSelector = documentSelector;
        this.torrentsElementsFactory = torrentsElementsFactory;
    }

    @Override
    public List<Torrent> parseDOMDocument(DOMDocument document) {
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
