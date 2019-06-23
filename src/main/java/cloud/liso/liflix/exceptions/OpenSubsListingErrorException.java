package cloud.liso.liflix.exceptions;

public class OpenSubsListingErrorException extends RuntimeException {
    public static final String DESCRIPTION = "List of subtitles Not Found";
    private static final long serialVersionUID = 2207561118854632L;

    public OpenSubsListingErrorException() {
        super(DESCRIPTION);
    }

    public OpenSubsListingErrorException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
