package cloud.liso.liflix.services.torrent.zooqle;

import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.torrent.parsingUtils.ElementsParser;
import cloud.liso.liflix.services.torrent.parsingUtils.Selectors;
import cloud.liso.liflix.services.torrent.parsingUtils.TorrentElements;
import cloud.liso.liflix.services.torrent.parsingUtils.TorrentElementsFactory;
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
    public TorrentElements createTorrentElements(DOMElement element) {
        return stringBasedParser.parse(element, selectors).concat(classBasedParser.parse(element, selectors));
    }
}
