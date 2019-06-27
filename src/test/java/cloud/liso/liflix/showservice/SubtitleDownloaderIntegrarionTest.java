package cloud.liso.liflix.showservice;

import cloud.liso.liflix.dto.OpenSubtitlesSubtitleDto;
import cloud.liso.liflix.exceptions.OpenSubsListingErrorException;
import cloud.liso.liflix.services.subtitles.RequestSubtitleDto;
import cloud.liso.liflix.services.subtitles.SubtitleDownloader;
import cloud.liso.liflix.services.subtitles.openSubtitles.OpenSubsDownloader;
import cloud.liso.liflix.services.subtitles.openSubtitles.OpenSubsRestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RestClientTest({OpenSubsDownloader.class, OpenSubsRestClient.class})
@ExtendWith(SpringExtension.class)
public class SubtitleDownloaderIntegrarionTest {

    @Autowired
    private SubtitleDownloader subtitleDownloader;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private List<OpenSubtitlesSubtitleDto> subs;

    private String expectJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        OpenSubtitlesSubtitleDto sub1 = OpenSubtitlesSubtitleDto.builder()
                .subFileName("Shadowhunters.The.Mortal.Instruments.S02E20.HDTV.x264-FLEET.srt")
                .downloadLink("https://dl.opensubtitles.org/en/download/src-api/vrf-19de0c5c/filead/1955648481.gz")
                .build();
        OpenSubtitlesSubtitleDto sub2 = OpenSubtitlesSubtitleDto.builder()
                .subFileName("Shadowhunters.The.Mortal.Instruments.S02E20.WEB.x264-TBS.srt")
                .downloadLink("https://dl.opensubtitles.org/en/download/src-api/vrf-19e30c61/filead/1955648486.gz")
                .build();
        OpenSubtitlesSubtitleDto sub3 = OpenSubtitlesSubtitleDto.builder()
                .subFileName("Shadowhunters.S02E20.Beside Still Water.srt")
                .downloadLink("https://dl.opensubtitles.org/en/download/src-api/vrf-19e80c5e/filead/1955882645.gz")
                .build();
        subs = new ArrayList<>(Arrays.asList(sub1, sub2, sub3));
        expectJson = objectMapper.writeValueAsString(subs);
    }

    @Test
    void getList_validRequest_returnsListOfSubtitles() {
//        this.server.expect(requestTo("https://rest.opensubtitles.org/search/episode-20/imdbid-4145054/moviebytesize-750005572/moviehash-319b23c54e9cf314/season-2/sublanguageid-eng"))
//                .andRespond(withSuccess(expectJson, MediaType.APPLICATION_JSON));
//        RequestSubtitleDto req = RequestSubtitleDto.builder()
//                .episode(20)
//                .hash("319b23c54e9cf314")
//                .size(750005572)
//                .season(2)
//                .imdb("4145054")
//                .language("eng")
//                .build();
//        List<SubtitleDto> list = subtitleDownloader.getList(req);
//        assertThat(list.isEmpty()).isFalse();
//        assertThat(list.size()).isEqualTo(3);
//        assertThat(list).containsExactlyElementsOf(subs);
    }

    @Test
    void getList_invalidRequest_throwOpensSubsListingErrorException() {
        this.server.expect(requestTo("https://rest.opensubtitles.org/search/episode-20/imdbid-4145054/moviebytesize-750005572/moviehash-319b23c54e9cf314/season-2/sublanguageid-eng"))
                .andRespond(withStatus(HttpStatus.BAD_REQUEST));
        RequestSubtitleDto req = RequestSubtitleDto.builder()
                .episode(20)
                .hash("319b23c54e9cf314")
                .size(750005572)
                .season(2)
                .imdb("4145054")
                .language("eng")
                .build();
        assertThrows(OpenSubsListingErrorException.class, () -> subtitleDownloader.getList(req));
    }
}
