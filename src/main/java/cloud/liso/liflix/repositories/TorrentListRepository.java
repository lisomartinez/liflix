package cloud.liso.liflix.repositories;

import cloud.liso.liflix.model.torrent.TorrentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface TorrentListRepository extends JpaRepository<TorrentList, String> {
    Optional<TorrentList> findByShowIdAndSeasonNumberAndEpisodeNumber(@NotNull int showId, int seasonNumber, int episodeNumber);
}
