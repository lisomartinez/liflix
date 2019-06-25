package cloud.liso.liflix.torrentfinder.service.torrent.zooqle.parsers;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.httpClient.DOMDocument;
import cloud.liso.liflix.services.httpClient.WebPage;
import cloud.liso.liflix.services.torrent.parsingUtils.ClassBasedElementsParser;
import cloud.liso.liflix.services.torrent.parsingUtils.StringBasedElementsParser;
import cloud.liso.liflix.services.torrent.parsingUtils.TorrentElementsFactory;
import cloud.liso.liflix.services.torrent.zooqle.ZooqleTorrentElementsFactory;
import cloud.liso.liflix.services.torrent.zooqle.client.ZooqleDOMDocument;
import cloud.liso.liflix.services.torrent.zooqle.client.ZooqleEpisodeParser;
import cloud.liso.liflix.services.torrent.zooqle.selectors.ZooqleSelectors;
import cloud.liso.liflix.services.torrent.zooqle.selectors.element.ZooqleDocumentSelector;
import cloud.liso.liflix.torrentfinder.utils.ZoogleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ZooqleEpisodeParser.class, ZooqleDocumentSelector.class, ZooqleTorrentElementsFactory.class, ClassBasedElementsParser.class, StringBasedElementsParser.class, ZooqleSelectors.class})
@ActiveProfiles("test")
class ZooqleEpisodeParserTest {

    private ZooqleEpisodeParser episodeParser;

    @Autowired
    private ZooqleDocumentSelector documentSelector;

    @Autowired
    private TorrentElementsFactory torrentsElementsFactory;

    @BeforeEach
    void setUp() {
        episodeParser = new ZooqleEpisodeParser(documentSelector, torrentsElementsFactory);
    }

    @Test
    void parseDOMDocument() {
//        WebPage webPage = webClient.fetchWebPage("https://zooqle.com/search?q=under+the+dome+s03e13");
        WebPage webPage = new WebPage(ZoogleUtils.getZooglePage());
        DOMDocument document = new ZooqleDOMDocument(webPage);

        List<Torrent> torrents = episodeParser.parseDOMDocument(document);

        Logger logger = LogManager.getLogger();
        torrents.forEach(logger::info);
        assertThat(torrents.size()).isEqualTo(6);
    }

}