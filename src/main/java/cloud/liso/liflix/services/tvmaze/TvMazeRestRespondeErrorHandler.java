package cloud.liso.liflix.services.tvmaze;

import cloud.liso.liflix.exceptions.TvMazeClientErrorException;
import cloud.liso.liflix.exceptions.TvMazeResourceNotFoundException;
import cloud.liso.liflix.exceptions.TvMazeServerErrorException;
import cloud.liso.liflix.exceptions.TvMazeTooManyRequestsException;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class TvMazeRestRespondeErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == CLIENT_ERROR || response.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        LogManager.getLogger().info(response);
        if (response.getStatusCode().series() == SERVER_ERROR) {
            throw new TvMazeServerErrorException(response.getStatusCode() + ". " + response.getStatusText());
        } else if (response.getStatusCode().series() == CLIENT_ERROR) {
            switch (response.getStatusCode()) {
                case TOO_MANY_REQUESTS:
                    throw new TvMazeTooManyRequestsException("API calls are rate limited to allow at least 20 calls every 10 seconds per IP address");
                case NOT_FOUND:
                    throw new TvMazeResourceNotFoundException("Tv Maze Resource Not Found");
                default:
                    throw new TvMazeClientErrorException(response.getStatusCode() + ". " + response.getStatusText());
            }
        }
    }
}
