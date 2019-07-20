package cloud.liso.liflix.services.torrent.sortpolicies;

import java.util.HashMap;
import java.util.Map;

public enum SortPolicyId {
    BEST_RES_MAX_SEEDERS("bestResolution"),
    BEST_SPEED("bestSpeed"),
    MAX_SEEDERS("mostSeeders"),
    ONLY_720_MAX_SEEDERS("720p"),
    DEFAULT_CRITERIA("default");

    private static final Map<String, SortPolicyId> BY_LABEL = new HashMap<>();

    static {
        for (SortPolicyId sc : values()) {
            BY_LABEL.put(sc.value, sc);
        }
    }

    private final String value;

    private SortPolicyId(String value) {
        this.value = value;
    }

    public static SortPolicyId valueOfParam(String param) {
        return BY_LABEL.get(param);
    }

}
