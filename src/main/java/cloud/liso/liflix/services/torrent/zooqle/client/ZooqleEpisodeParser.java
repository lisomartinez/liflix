package cloud.liso.liflix.services.torrent.zooqle.client;

import cloud.liso.liflix.exceptions.EpisodeParsingException;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.httpClient.DOMDocument;
import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.torrent.EpisodeParser;
import cloud.liso.liflix.services.torrent.parsingUtils.TorrentElements;
import cloud.liso.liflix.services.torrent.parsingUtils.TorrentElementsFactory;
import cloud.liso.liflix.services.torrent.zooqle.selectors.element.ZooqleDocumentSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ZooqleEpisodeParser implements EpisodeParser {

    private static final Pattern RELEASE_TYPE = Pattern.compile("(?i)((?:PPV\\.)?[HP]DTV|(?:HD)?CAM|B[DR]Rip|(?:HD-?)?TS|(?:PPV )" +
            "?WEB-?DL(?: DVDRip)?|HDRip|DVDRip|DVDRIP|CamRip|W[EB]BRip|BluRay|DvDScr|hdtv|telesync)");

    private static final Pattern CODEC = Pattern.compile("(?i)(.*((xvid)|(x264)).*)");
    private static final Pattern RESOLUTION = Pattern.compile("(?i)([0-9]{3,4}p)");

    private ZooqleDocumentSelector documentSelector;
    private TorrentElementsFactory torrentsElementsFactory;

    @Autowired
    public ZooqleEpisodeParser(ZooqleDocumentSelector documentSelector, TorrentElementsFactory torrentsElementsFactory) {
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
        TorrentElements elements = torrentsElementsFactory.createTorrentElements(element);
        return Torrent.builder()
                .title(elements.getTitle())
                .magnetLink(elements.getMagnet())
                .resolution(elements.getResolution())
                .codec(elements.getCodec())
                .releaseType(elements.getReleaseType())
                .size(elements.getSize())
                .seeders(elements.getSeeders())
                .leechers(elements.getLeedchers())
                .build();
    }
}
