package cloud.liso.liflix.controllers.torrents;

import cloud.liso.liflix.dto.TorrentDto;
import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.services.api.torrent.TorrentService;
import cloud.liso.liflix.services.api.torrent.TorrentSortCriteria;
import cloud.liso.liflix.services.impl.searchEngine.SortCriteria;
import cloud.liso.liflix.services.impl.searchEngine.SortCriteriaMap;
import cloud.liso.liflix.services.impl.torrent.RequestParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/torrents")
public class TorrentsController {
    public static final String ALL_TORRENTS = "/all";
    public static final String BEST = "/best";

    private TorrentService torrentService;
    private RequestParser requestParser;
    private ModelMapper mapper;
    private SortCriteriaMap sortCriteriaMap;

    @Autowired
    public TorrentsController(TorrentService torrentService, RequestParser requestParser, ModelMapper mapper,
                              SortCriteriaMap sortCriteriaMap) {
        this.torrentService = torrentService;
        this.requestParser = requestParser;
        this.sortCriteriaMap = sortCriteriaMap;
        this.mapper = mapper;
    }

    @GetMapping(ALL_TORRENTS)
    @ResponseStatus(HttpStatus.OK)
    public List<TorrentDto> getTorrents(@RequestParam Map<String, String> params) {
        Request req = requestParser.from(params);
        SortCriteria sortCriteria = SortCriteria.valueOfLabel(params.get("mode"));
        TorrentSortCriteria criteria = sortCriteriaMap.getOrDefault(sortCriteria);
        return torrentService.getTorrents(req, criteria)
                .stream()
                .map(t -> mapper.map(t, TorrentDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(BEST)
    @ResponseStatus(HttpStatus.OK)
    public TorrentDto getBest(@RequestParam Map<String, String> params) {
        Request request = requestParser.from(params);
        SortCriteria sortCriteria = SortCriteria.valueOfLabel(params.get("mode"));
        TorrentSortCriteria criteria = sortCriteriaMap.getOrDefault(sortCriteria);
        return mapper.map(torrentService.getTorrent(request, criteria), TorrentDto.class);
    }
}
