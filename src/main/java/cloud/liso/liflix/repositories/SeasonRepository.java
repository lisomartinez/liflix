package cloud.liso.liflix.repositories;

import cloud.liso.liflix.model.show.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {

    @Query("SELECT s FROM Season s WHERE show_id = :id")
    List<Season> findAllByShow(@Param("id") int id);

    @Query("SELECT s FROM Season s WHERE show_id = :showId AND s.number = :seasonNumber")
    Optional<Season> getSeasonsByShowId(@Param("showId") int showId, @Param("seasonNumber") int seasonNumber);
}
