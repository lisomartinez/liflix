package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSortPolicyException extends RuntimeException {
    public static final String DESCRIPTION = "Page Not Found";
    private static final long serialVersionUID = 212075667688546372L;

    public InvalidSortPolicyException() {
        super(DESCRIPTION);
    }

    public InvalidSortPolicyException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
