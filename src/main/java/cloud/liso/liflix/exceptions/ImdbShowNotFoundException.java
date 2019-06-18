package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImdbShowNotFoundException extends RuntimeException {
    public static final String DESCRIPTION = "IMDB page not found exception";
    private static final long serialVersionUID = 3839756676887746373L;

    public ImdbShowNotFoundException() {
        super(DESCRIPTION);
    }

    public ImdbShowNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
