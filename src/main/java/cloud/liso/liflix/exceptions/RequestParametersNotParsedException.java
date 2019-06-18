package cloud.liso.liflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestParametersNotParsedException extends RuntimeException {
    public static final String DESCRIPTION = "Error parsing request parameters";
    private static final long serialVersionUID = 11107526768877372L;

    public RequestParametersNotParsedException() {
        super(DESCRIPTION);
    }

    public RequestParametersNotParsedException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
