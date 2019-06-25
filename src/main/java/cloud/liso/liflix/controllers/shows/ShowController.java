package cloud.liso.liflix.controllers.shows;

import cloud.liso.liflix.dto.GenreDto;
import cloud.liso.liflix.dto.ShowCardDto;
import cloud.liso.liflix.dto.ShowDto;
import cloud.liso.liflix.model.show.ShowCard;
import cloud.liso.liflix.services.show.ShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

@Api(tags = "Shows")

@RestController
public class ShowController {

    public static final String SHOWS = "/shows";
    public static final String SHOW_ID = "/{id}";
    public static final String INDEX = "/index";
    public static final String GENRES = "/genres";
    public static final String NAME = "/{name}";
    private ShowService showService;

    private ModelMapper modelMapper;

    @Autowired
    public ShowController(ShowService showService, ModelMapper modelMapper) {
        this.showService = showService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(SHOWS + SHOW_ID)
    @ResponseStatus(OK)
    @ApiOperation(value = "get a show by id")
    public ShowDto getShow(@PathVariable int id) {
        return modelMapper.map(showService.getShowById(id), ShowDto.class);
    }

    @GetMapping(SHOWS)
    @ResponseStatus(OK)
    @ApiOperation(value = "get show page")
    public Page<ShowDto> getShowPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "25") int size) {
        return showService.getShowPage(page, size).map(show -> modelMapper.map(show, ShowDto.class));
    }

    @GetMapping(SHOWS + INDEX)
    @ResponseStatus(OK)
    @ApiOperation(value = "get index page")
    public Map<GenreDto, List<ShowCardDto>> getIndexPage() {
        return showService.getIndex().entrySet()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<GenreDto, List<ShowCardDto>> convertToDto(Map.Entry<String, List<ShowCard>> card) {
        return new HashMap.SimpleImmutableEntry<>(GenreDto.of(card.getKey()),
                card.getValue().stream()
                        .map(show -> modelMapper.map(show, ShowCardDto.class))
                        .collect(toList()));
    }

    @GetMapping(SHOWS + GENRES + NAME)
    @ResponseStatus(OK)
    public Page<ShowCardDto> getGenrePage(@PathVariable String name,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return showService.getPageByGenre(name, page, size);
    }
}
