package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImdbNotAvailableException extends RuntimeException {
    public static final String DESCRIPTION = "Show has not a IMDB link";
    private static final long serialVersionUID = 4839756676887746374L;

    public ImdbNotAvailableException() {
        super(DESCRIPTION);
    }

    public ImdbNotAvailableException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
