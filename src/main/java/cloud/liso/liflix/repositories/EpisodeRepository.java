package cloud.liso.liflix.repositories;

import cloud.liso.liflix.model.show.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    @Query("SELECT ep FROM Episode ep INNER JOIN ep.season s INNER JOIN s.show sh WHERE sh.id = :showId AND s.number = :seasonNumber and s.episodes = :episodeNumber")
    Optional<Episode> findByShowSeasonEpisode(@Param("showId") int showId, @Param("seasonNumber") int seasonNumber, @Param("episodeNumber") int episodeNumber);
}
