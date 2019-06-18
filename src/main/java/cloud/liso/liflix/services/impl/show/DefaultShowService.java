package cloud.liso.liflix.services.impl.show;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.exceptions.PageNotFoundException;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.model.show.Genre;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.model.show.ShowCard;
import cloud.liso.liflix.repositories.GenreRepository;
import cloud.liso.liflix.repositories.ShowRepository;
import cloud.liso.liflix.services.api.show.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultShowService implements ShowService {

    private ShowRepository showRepository;

    private GenreRepository genreRepository;

    @Autowired
    public DefaultShowService(ShowRepository showRepository, GenreRepository genreRepository) {
        this.showRepository = showRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Show getShowById(int showId) {
        return showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("show id =" + showId));
    }

    @Override
    public Page<Show> getShowPage(int page, int size) {
        return showRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<ShowCardDto> getPageByGenre(String name, int page, int size) {
        return genreRepository.findGenreByName(name, PageRequest.of(page, size));
    }

    @Override
    public Map<String, List<ShowCard>> getIndex() {
        try {
            List<Genre> genres = genreRepository.findAll();
            Map<String, List<ShowCard>> portal = new HashMap<>();
            for (Genre genre : genres) {
                List<ShowCard> shows = genre.getShows()
                        .stream()
                        .limit(10)
                        .map(sh -> createShowCard(sh, genre.getName()))
                        .collect(Collectors.toList());
                portal.put(genre.getName(), shows);
            }
            return portal;
        } catch (Exception ex) {
            throw new PageNotFoundException("Index page not found");
        }

    }

    private ShowCard createShowCard(Show show, String genre) {
        return ShowCard.builder()
                .genre(genre)
                .image(show.getImage())
                .name(show.getName())
                .rating(show.getRating())
                .seasons(show.getGenres().size())
                .build();
    }
}
