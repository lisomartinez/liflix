package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WebClientErrorException extends RuntimeException {
    public static final String DESCRIPTION = "Web client error exception";
    private static final long serialVersionUID = 2839756676887746372L;

    public WebClientErrorException() {
        super(DESCRIPTION);
    }

    public WebClientErrorException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
