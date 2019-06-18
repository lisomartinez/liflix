package cloud.liso.liflix.controllers.shows;

import cloud.liso.liflix.services.api.imdb.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/shows/imdb")
public class ImdbController {
    public static final String SHOWS = "/shows";
    public static final String IMDB = "/shows/imdb";
    public static final String ID = "/{id}";
    private ImdbService httpImdbService;

    @Autowired
    public ImdbController(@Qualifier("httpImdbService") ImdbService imdbService) {
        this.httpImdbService = imdbService;
    }

    @GetMapping(value = ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public String getImdbUrl(@PathVariable int id) {
        return httpImdbService.getImdbVideoLink(id);
    }

}
