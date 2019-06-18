package cloud.liso.liflix.exceptions;

public class EpisodeParsingException extends RuntimeException {
    public static final String DESCRIPTION = "Error parsing torrent episode";
    private static final long serialVersionUID = 1107526768877372L;

    public EpisodeParsingException() {
        super(DESCRIPTION);
    }

    public EpisodeParsingException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
