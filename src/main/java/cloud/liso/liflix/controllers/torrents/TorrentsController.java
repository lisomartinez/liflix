package cloud.liso.liflix.controllers.torrents;

import cloud.liso.liflix.dto.TorrentDto;
import cloud.liso.liflix.exceptions.InvalidSortPolicyException;
import cloud.liso.liflix.model.torrent.Request;
import cloud.liso.liflix.services.torrent.TorrentService;
import cloud.liso.liflix.services.torrent.parsing.RequestParser;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicies;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicy;
import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicyId;
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
    public static final String TORRENTS = "/torrents";
    public static final String ALL_TORRENTS = "/all";
    public static final String BEST = "/best";

    private TorrentService torrentService;
    private RequestParser requestParser;
    private ModelMapper mapper;
    private SortPolicies sortPolicies;

    @Autowired
    public TorrentsController(TorrentService torrentService, RequestParser requestParser, ModelMapper mapper,
                              SortPolicies sortPolicies) {
        this.torrentService = torrentService;
        this.requestParser = requestParser;
        this.sortPolicies = sortPolicies;
        this.mapper = mapper;
    }

    @GetMapping(ALL_TORRENTS)
    @ResponseStatus(HttpStatus.OK)
    public List<TorrentDto> getTorrents(@RequestParam Map<String, String> params) {
        Request req = requestParser.from(params);
        SortPolicyId sortPolicyId = getSortPolicyId(params.getOrDefault("mode", "default"));
        SortPolicy policy = sortPolicies.getOrDefault(sortPolicyId);
        return torrentService.getTorrents(req, policy)
                .stream()
                .map(t -> mapper.map(t, TorrentDto.class))
                .collect(Collectors.toList());
    }

    private SortPolicyId getSortPolicyId(String policy) {
        try {
            return SortPolicyId.valueOfParam(policy);
        } catch (Exception ex) {
            throw new InvalidSortPolicyException(policy);
        }

    }

    @GetMapping(BEST)
    @ResponseStatus(HttpStatus.OK)
    public TorrentDto getBest(@RequestParam Map<String, String> params) {
        Request request = requestParser.from(params);
        SortPolicyId sortPolicyId = getSortPolicyId(params.get("mode"));
        SortPolicy criteria = sortPolicies.getOrDefault(sortPolicyId);
        return mapper.map(torrentService.getTorrent(request, criteria), TorrentDto.class);
    }
}
