package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ZoogleWebClientErrorException extends RuntimeException {
    public static final String DESCRIPTION = "Page Not Found";
    private static final long serialVersionUID = 1120751676823154639L;

    public ZoogleWebClientErrorException() {
        super(DESCRIPTION);
    }

    public ZoogleWebClientErrorException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
