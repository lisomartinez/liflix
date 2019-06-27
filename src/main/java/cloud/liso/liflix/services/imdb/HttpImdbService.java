package cloud.liso.liflix.services.imdb;


import cloud.liso.liflix.exceptions.ImdbNotAvailableException;
import cloud.liso.liflix.exceptions.ShowNotFoundException;
import cloud.liso.liflix.repositories.ShowRepository;
import cloud.liso.liflix.services.httpClient.WebClient;
import cloud.liso.liflix.services.httpClient.WebPage;
import cloud.liso.liflix.services.httpClient.WebPageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HttpImdbService implements ImdbService {

    public static final String IMDB_BASE_URL = "https://www.imdb.com/videoembed/";
    private static final String NOT_AVAILABLE = "N/A";
    private WebClient client;
    private WebPageParser parse;
    private ShowRepository showRepository;

    @Autowired
    public HttpImdbService(WebClient client, WebPageParser parse, ShowRepository showRepository) {
        this.client = client;
        this.parse = parse;
        this.showRepository = showRepository;
    }

    //TODO: CHANGE TO ID INSTEAD OF FULL URL.
    @Override
    @Cacheable("imdb")
    public String getImdbVideoLink(int showId) {
        String imdbURL = showRepository.getImdbURL(showId).orElseThrow(() -> new ShowNotFoundException("Show id=" + showId + " not found"));
        if (imdbURL.equals(NOT_AVAILABLE)) {
            throw new ImdbNotAvailableException("Show id=" + showId + "has not a imdb link");
        } else {
            WebPage webpage = client.fetchWebPage(imdbURL);
            return IMDB_BASE_URL + parse.parseWebpage(webpage);
        }
    }
}
