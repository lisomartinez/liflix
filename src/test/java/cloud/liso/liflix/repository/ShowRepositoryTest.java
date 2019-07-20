package cloud.liso.liflix.repository;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.exceptions.WebPageNotFoundException;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.repositories.ShowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
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
        Show show = showRepository.findById(1).orElseThrow(ShowNotFoundException::new);
        assertThat(show).isNotNull();
    }

    @Test
    void getShowCardPage_returnShowCardDtoPageWithInfoOfShowS() throws IOException {
        Show show = showRepository.findById(1).get();
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
        Show show = showRepository.findById(1).get();
        String imdbURL = showRepository.getImdbURL(show.getId()).orElseThrow(WebPageNotFoundException::new);
        assertThat(imdbURL).isEqualTo(show.getImdb());
    }
}