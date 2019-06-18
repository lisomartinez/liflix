package cloud.liso.liflix.repositories;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.model.show.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("SELECT new cloud.liso.liflix.dto.ShowCardDto(" +
            "show.id, show.name, show.image, show.rating, count(show.id)) " +
            "FROM Genre genre INNER JOIN genre.shows show" +
            " WHERE genre.name = ?1" +
            " GROUP BY show.id ORDER BY show.id")
    Page<ShowCardDto> findGenreByName(String name, PageRequest page);
}
