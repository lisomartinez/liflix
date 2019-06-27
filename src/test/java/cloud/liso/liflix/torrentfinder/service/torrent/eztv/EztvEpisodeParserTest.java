package cloud.liso.liflix.torrentfinder.service.torrent.eztv;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.httpClient.DOMDocument;
import cloud.liso.liflix.services.httpClient.JsoupDOMDocument;
import cloud.liso.liflix.services.httpClient.WebPage;
import cloud.liso.liflix.services.torrent.implementations.eztv.client.EztvEpisodeParser;
import cloud.liso.liflix.services.torrent.implementations.eztv.selectors.EztvDocumentSelector;
import cloud.liso.liflix.services.torrent.implementations.eztv.selectors.EztvSelectors;
import cloud.liso.liflix.services.torrent.implementations.eztv.selectors.EztvTorrentElementsFactory;
import cloud.liso.liflix.services.torrent.parsing.ClassBasedElementsParser;
import cloud.liso.liflix.services.torrent.parsing.StringBasedElementsParser;
import cloud.liso.liflix.services.torrent.parsing.TorrentElementsFactory;
import cloud.liso.liflix.torrentfinder.utils.TorrentsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EztvEpisodeParserTest.class, EztvDocumentSelector.class, EztvTorrentElementsFactory.class, ClassBasedElementsParser.class, StringBasedElementsParser.class, EztvSelectors.class})
@ActiveProfiles("test")
public class EztvEpisodeParserTest {

    private EztvEpisodeParser episodeParser;

    @Autowired
    private EztvDocumentSelector documentSelector;

    @Autowired
    @Qualifier(value = "eztvTorrentElementsFactory")
    private TorrentElementsFactory torrentsElementsFactory;

    @BeforeEach
    void setUp() {
        episodeParser = new EztvEpisodeParser(documentSelector, torrentsElementsFactory);
    }

    @Test
    void parseDOMDocument() {
//        WebPage webPage = webClient.fetchWebPage("https://zooqle.com/search?q=under+the+dome+s03e13");
        Logger logger = LogManager.getLogger();
        WebPage webPage = new WebPage(TorrentsUtils.getEztvPage());
        DOMDocument document = new JsoupDOMDocument(webPage);

        List<Torrent> torrents = episodeParser.parseDOMDocument(document);

        torrents.forEach(logger::info);
        assertThat(torrents.size()).isEqualTo(7);
    }
}
