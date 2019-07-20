package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WebPageNotFoundException extends RuntimeException {
    public static final String DESCRIPTION = "Web page not found exception";
    private static final long serialVersionUID = 3839756676887746373L;

    public WebPageNotFoundException() {
        super(DESCRIPTION);
    }

    public WebPageNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
