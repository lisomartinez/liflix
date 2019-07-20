package cloud.liso.liflix.services.torrent.implementations.eztv;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.torrent.parsing.ClassBasedElementsParser;
import cloud.liso.liflix.services.torrent.parsing.Selectors;
import cloud.liso.liflix.services.torrent.parsing.StringBasedElementsParser;
import cloud.liso.liflix.services.torrent.parsing.TorrentElementsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EztvTorrentElementsFactory implements TorrentElementsFactory {

    private ClassBasedElementsParser classBasedElementsParser;
    private StringBasedElementsParser stringBasedElementsParser;
    private Selectors selectors;

    @Autowired
    public EztvTorrentElementsFactory(ClassBasedElementsParser classBasedElementsParser,
                                      StringBasedElementsParser stringBasedElementsParser,
                                      @Qualifier("eztvSelectors") Selectors selectors) {
        this.classBasedElementsParser = classBasedElementsParser;
        this.stringBasedElementsParser = stringBasedElementsParser;
        this.selectors = selectors;
    }

    @Override
    public Torrent.TorrentBuilder createTorrentElements(DOMElement element, Torrent.TorrentBuilder builder) {
        stringBasedElementsParser.parse(element, selectors, builder);
        classBasedElementsParser.parse(element, selectors, builder);
        return builder;
    }
}
