package cloud.liso.liflix.services.torrent.implementations.zooqle;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMElement;
import cloud.liso.liflix.services.torrent.parsing.ElementsParser;
import cloud.liso.liflix.services.torrent.parsing.Selectors;
import cloud.liso.liflix.services.torrent.parsing.TorrentElementsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ZooqleTorrentElementsFactory implements TorrentElementsFactory {

    private ElementsParser stringBasedParser;
    private ElementsParser classBasedParser;
    private Selectors selectors;

    @Autowired
    public ZooqleTorrentElementsFactory(@Qualifier("stringBasedElementsParser") ElementsParser stringBasedParser,
                                        @Qualifier("classBasedElementsParser") ElementsParser classBasedParser,
                                        @Qualifier("zooqleSelectors") Selectors selectors) {
        this.stringBasedParser = stringBasedParser;
        this.classBasedParser = classBasedParser;
        this.selectors = selectors;
    }

    @Override
    public Torrent.TorrentBuilder createTorrentElements(DOMElement element, Torrent.TorrentBuilder builder) {
        stringBasedParser.parse(element, selectors, builder);
        classBasedParser.parse(element, selectors, builder);
        return builder;
    }
}
