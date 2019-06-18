package cloud.liso.liflix.repositories;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.model.show.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query("SELECT new cloud.liso.liflix.dto.ShowCardDto(" +
            "show.id, show.name, show.image, show.rating, count(show.id)) " +
            "FROM Show show INNER JOIN show.seasons" +
            " GROUP BY show.id ORDER BY show.id")
    Page<ShowCardDto> getShowCardPage(PageRequest pageRequest);

    @Query("SELECT show.imdb FROM Show show WHERE show.id = ?1")
    Optional<String> getImdbURL(int showId);
}
