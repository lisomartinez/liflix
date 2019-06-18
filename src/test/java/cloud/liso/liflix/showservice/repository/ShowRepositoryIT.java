package cloud.liso.liflix.showservice.repository;

import cloud.liso.liflix.model.show.Genre;
import cloud.liso.liflix.model.show.Schedule;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.repositories.ShowRepository;
import cloud.liso.liflix.showservice.utils.ShowFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShowRepositoryIT {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void save_validShow_returnsSaveShow() {
        Show expected = ShowFactory.getShowWithGenreAndSchedule(LocalDateTime.of(LocalDate.of(2010, 3, 12), LocalTime.of(22, 11, 11)), "Ended");

        Show toSave = ShowFactory.getShowBase();
        toSave.setGenres(new ArrayList<>());
        for (Genre genre : getGenres()) {
            toSave.addGenre(genre);
        }
        toSave.setSchedule(getSchedule());
        toSave.setStatus("Ended");
        toSave.setLastUpdate(LocalDateTime.of(LocalDate.of(2010, 3, 12), LocalTime.of(22, 11, 11)));

        Show savedShow = showRepository.save(toSave);

        assertThat(savedShow).isEqualTo(expected);
    }

    @Test
    void getShowById_ReturnsShow() {
        Show expected = ShowFactory.getShowWithGenreAndSchedule(LocalDateTime.of(LocalDate.of(2010, 3, 12), LocalTime.of(22, 11, 11)), "Ended");
        Show show = showRepository.findById(expected.getId()).get();
        assertThat(show).isEqualTo(expected);

    }

    private Schedule getSchedule() {
        return em.find(Schedule.class, 1);
    }

    private List<Genre> getGenres() {
        List<Genre> genres = ShowFactory.getGenres();
        List<Genre> saves = new ArrayList<>();
        for (Genre genre : genres) {
            Genre e = em.find(Genre.class, genre.getId());
            saves.add(e);
        }
        return saves;
    }

    @Test
    void getImdbLink_validShowId_ReturnsImdbLink() {
        Show show = ShowFactory.getShowBase();
        String imdbURL = showRepository.getImdbURL(show.getId()).get();
        assertThat(imdbURL).isEqualTo(show.getImdb());
    }
}