package cloud.liso.liflix.services.torrent.parsing;


import cloud.liso.liflix.exceptions.RequestParametersNotParsedException;
import cloud.liso.liflix.model.torrent.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RequestParser {
    public static final String SHOW_ID = "showId";
    public static final String SHOW_NAME = "showName";
    public static final String SEASON_NUMBER = "seasonNumber";
    public static final String EPISODE_NUMBER = "episodeNumber";

    public Request from(Map<String, String> params) {
        try {
            int showId = Integer.parseInt(params.get(SHOW_ID));
            String showName = params.get(SHOW_NAME);
            if (StringUtils.isBlank(showName)) throw new IllegalArgumentException();
            int seasonNumber = Integer.parseInt(params.get(SEASON_NUMBER));
            int episodeNumber = Integer.parseInt(params.get(EPISODE_NUMBER));
            return Request.builder()
                    .showId(showId)
                    .showName(showName)
                    .seasonNumber(seasonNumber)
                    .episodeNumber(episodeNumber)
                    .build();
        } catch (Exception ex) {
            throw new RequestParametersNotParsedException(params.toString());
        }
    }

}
