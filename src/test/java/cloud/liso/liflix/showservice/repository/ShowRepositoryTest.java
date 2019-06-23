package cloud.liso.liflix.showservice.repository;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.exceptions.ImdbShowNotFoundException;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.repositories.ShowRepository;
import cloud.liso.liflix.showservice.utils.ShowFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShowRepositoryTest {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    void getShowById_ReturnsShow() throws IOException {
        Show expected = ShowFactory.getShowComplete(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0));
        Show show = ShowFactory.saveShow(em);
        assertThat(show).isEqualTo(expected);
        showRepository.findById(expected.getId()).orElseThrow(ShowNotFoundException::new);
    }

    @Test
    void getShowCardPage_returnShowCardDtoPageWithInfoOfShowS() throws IOException {
        Show show = ShowFactory.saveShow(em);
        ShowCardDto expected = ShowCardDto.builder()
                .id(show.getId())
                .image(show.getImage())
                .name(show.getName())
                .rating(show.getRating())
                .seasons(show.getSeasons().size())
                .build();

        List<ShowCardDto> content = showRepository.getShowCardPage(PageRequest.of(0, 1)).getContent();

        assertThat(content).isNotNull();
        assertThat(content.isEmpty()).isFalse();
        assertThat(content.get(0)).isEqualTo(expected);
    }

    @Test
    void getImdbOfShow_validShow_returnStringWithIMDBLink() throws IOException {
        Show show = ShowFactory.saveShow(em);
        String imdbURL = showRepository.getImdbURL(show.getId()).orElseThrow(ImdbShowNotFoundException::new);
        assertThat(imdbURL).isEqualTo(show.getImdb());
    }
}