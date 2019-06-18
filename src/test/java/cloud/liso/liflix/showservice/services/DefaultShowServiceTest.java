package cloud.liso.liflix.showservice.services;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.model.show.Genre;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.model.show.ShowCard;
import cloud.liso.liflix.repositories.GenreRepository;
import cloud.liso.liflix.repositories.ShowRepository;
import cloud.liso.liflix.services.api.show.ShowService;
import cloud.liso.liflix.services.impl.show.DefaultShowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultShowServiceTest {

    private ShowService showService;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        showService = new DefaultShowService(showRepository, genreRepository);
    }

    @Test
    void getShowById_validId_ReturnsShow() {
        final int id = 1;
        Show expected = Mockito.mock(Show.class);
        when(showRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        Show result = showService.getShowById(1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getShowById_InvalidId_ThrowsShowNotFoundException() {
        final int id = Integer.MAX_VALUE;
        when(showRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ShowNotFoundException.class, () -> showService.getShowById(id));
    }

    @Test
    void getShowPage() {
        List<Show> shows = new ArrayList<>(Arrays.asList(Mockito.mock(Show.class)));
        Page<Show> page = new PageImpl<>(shows, PageRequest.of(0, 1), shows.size());
        when(showRepository.findAll(PageRequest.of(0, 1))).thenReturn(page);
        Page<Show> result = showService.getShowPage(0, 1);
        assertThat(result).isEqualTo(page);
    }

    @Test
    void getPageByGenre() {
        List<ShowCardDto> showCards = new ArrayList<>(Arrays.asList(Mockito.mock(ShowCardDto.class)));
        Page<ShowCardDto> page = new PageImpl<>(showCards, PageRequest.of(0, 1), showCards.size());
        when(genreRepository.findGenreByName("genre", PageRequest.of(0, 1))).thenReturn(page);
        Page<ShowCardDto> res = showService.getPageByGenre("genre", 0, 1);
        assertThat(res).isEqualTo(page);
    }

    @Test
    void getIndex() throws IOException {
        Show show = Show.builder()
                .id(1)
                .name("Under the Dome")
                .tvMaze("http://www.tvmaze.com/shows/1/under-the-dome")
                .type("Scripted")
                .language("English")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .imdb("https://www.imdb.com/title/tt1553656")
                .image("http://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg")
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.")
                .genres(new ArrayList<>())
                .build();

        Genre drama = Genre.of("Drama", new ArrayList<>());
        show.addGenre(drama);

        List<Genre> genres = show.getGenres();
        ShowCard showCard = ShowCard.builder()
                .genre(drama.getName())
                .image(show.getImage())
                .name(show.getName())
                .rating(show.getRating())
                .seasons(show.getGenres().size())
                .build();

        when(genreRepository.findAll()).thenReturn(genres);
        Map<String, List<ShowCard>> res = showService.getIndex();
        assertThat(res.size()).isEqualTo(1);
        assertThat(res.values()).containsExactlyInAnyOrder(Arrays.asList(showCard));
    }
}