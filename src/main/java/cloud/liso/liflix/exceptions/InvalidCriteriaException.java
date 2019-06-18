package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCriteriaException extends RuntimeException {
    public static final String DESCRIPTION = "Page Not Found";
    private static final long serialVersionUID = 212075667688546372L;

    public InvalidCriteriaException() {
        super(DESCRIPTION);
    }

    public InvalidCriteriaException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
