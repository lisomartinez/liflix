package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {
    public static final String DESCRIPTION = "Page Not Found";
    private static final long serialVersionUID = 112075667688546379L;

    public PageNotFoundException() {
        super(DESCRIPTION);
    }

    public PageNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
