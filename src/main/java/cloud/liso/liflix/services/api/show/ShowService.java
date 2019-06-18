package cloud.liso.liflix.services.api.show;

import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.model.show.Show;
import cloud.liso.liflix.model.show.ShowCard;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ShowService {

    Show getShowById(int showId);

    Page<Show> getShowPage(int page, int size);

    Page<ShowCardDto> getPageByGenre(String name, int page, int size);

    Map<String, List<ShowCard>> getIndex();

}
