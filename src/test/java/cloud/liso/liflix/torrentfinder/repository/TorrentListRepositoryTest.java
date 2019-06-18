package cloud.liso.liflix.torrentfinder.repository;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.model.torrent.TorrentList;
import cloud.liso.liflix.repositories.TorrentListRepository;
import cloud.liso.liflix.torrentfinder.utils.TorrentFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class TorrentListRepositoryTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private TorrentListRepository torrentInfoRepository;

    @Test
    void saveTorrentInfo_ShouldReturnSavedInstance() {
        TorrentList torrentList = TorrentFactory.createTorrentInfo();
        TorrentList saved = torrentInfoRepository.save(torrentList);

        log.info("------------------------------------------> " + saved);

        assertThat(saved).isEqualTo(torrentList);
    }

    @Test
    void saveTorrentInfoAndRemoveTorrent_ShouldRetrieveUpdatedContent() {
        TorrentList torrentList = TorrentFactory.createTorrentInfo();
        TorrentList saved = torrentInfoRepository.save(torrentList);

        log.info("------------------------------------------> " + saved);
        assertThat(saved).isEqualTo(torrentList);

        saved.getTorrents().remove(0);
        saved = torrentInfoRepository.save(saved);
        log.info("------------------------------------------> " + saved);
        assertThat(saved.getTorrents().size()).isEqualTo(0);
    }

    @Test
    void saveTorrentInfoAndAddTorrent_ShouldRetrieveUpdatedContent() {
        TorrentList torrentList = TorrentFactory.createTorrentInfo();
        TorrentList saved = torrentInfoRepository.save(torrentList);

        log.info("------------------------------------------> " + saved);
        assertThat(saved).isEqualTo(torrentList);

        saved.getTorrents().add(Torrent.builder().build());
        saved.getTorrents().add(Torrent.builder().build());
        saved.getTorrents().add(Torrent.builder().build());

        TorrentList result = torrentInfoRepository.save(saved);

        assertThat(result.getTorrents().size()).isEqualTo(4);
    }

    @Test
    void findByShowId_validId_ShouldReturnInstance() {
        TorrentList torrentList = TorrentFactory.createTorrentInfo();
        TorrentList saved = torrentInfoRepository.save(torrentList);

        Optional<TorrentList> response = torrentInfoRepository.findByShowIdAndSeasonNumberAndEpisodeNumber(torrentList.getShowId(), 1, 2);
        assertThat(response.get()).isEqualTo(torrentList);
    }
}