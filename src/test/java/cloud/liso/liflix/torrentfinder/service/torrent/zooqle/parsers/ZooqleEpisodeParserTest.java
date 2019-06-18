package cloud.liso.liflix.torrentfinder.service.torrent.zooqle.parsers;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.repositories.torrent.CodecRepository;
import cloud.liso.liflix.repositories.torrent.ReleaseTypeRepository;
import cloud.liso.liflix.repositories.torrent.ResolutionRepository;
import cloud.liso.liflix.services.api.httpClient.DOMDocument;
import cloud.liso.liflix.services.api.httpClient.WebPage;
import cloud.liso.liflix.services.impl.torrent.zooqle.ZooqleDOMDocument;
import cloud.liso.liflix.services.impl.torrent.zooqle.parsers.ZooqleEpisodeParser;
import cloud.liso.liflix.services.impl.torrent.zooqle.selectors.element.*;
import cloud.liso.liflix.torrentfinder.utils.ZoogleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ZooqleEpisodeParserTest {

    private ZooqleEpisodeParser episodeParser;

    @Mock
    private CodecRepository codecRepository;

    @Mock
    private ResolutionRepository resolutionRepository;

    @Mock
    private ReleaseTypeRepository releaseTypeRepository;

    @BeforeEach
    void setUp() {
        episodeParser = new ZooqleEpisodeParser(new ZoogleDocumentSelector(),
                new ZoogleTitleElementSelector(),
                new ZoogleSizeElementSelector(),
                new ZoogleMagnetLinkElementSelector(),
                new ZoogleLeechersElementSelector(),
                new ZoogleSeederElementSelector());
    }

    @Test
    void parseDOMDocument() {
//        WebPage webPage = webClient.fetchWebPage("https://zooqle.com/search?q=under+the+dome+s03e13");
        WebPage webPage = new WebPage(ZoogleUtils.getZooglePage());
        DOMDocument document = new ZooqleDOMDocument(webPage);
        List<Torrent> torrents = episodeParser.parseDOMDocument(document);

        Logger logger = LogManager.getLogger();
        torrents.stream().forEach(logger::info);
        assertThat(torrents.size()).isEqualTo(6);
    }

}