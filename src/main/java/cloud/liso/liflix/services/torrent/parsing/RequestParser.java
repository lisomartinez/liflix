package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.exceptions.RequestParametersNotParsedException;
import cloud.liso.liflix.model.torrent.Request;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RequestParser {
    private static final String SHOW_ID = "showId";
    private static final String SHOW_NAME = "showName";
    private static final String SEASON_NUMBER = "seasonNumber";
    private static final String EPISODE_NUMBER = "episodeNumber";

    public Request from(Map<String, String> params) {
        try {
            int showId = Integer.valueOf(params.get(SHOW_ID));
            String showName = params.get(SHOW_NAME);
            int seasonNumber = Integer.valueOf(params.get(SEASON_NUMBER));
            int episodeNumber = Integer.valueOf(params.get(EPISODE_NUMBER));
            return new Request(showId, showName, seasonNumber, episodeNumber);
        } catch (Exception ex) {
            throw new RequestParametersNotParsedException(params.toString());
        }
    }

}
