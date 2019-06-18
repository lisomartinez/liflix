package cloud.liso.liflix.services.impl.searchEngine;

import java.util.HashMap;
import java.util.Map;

public enum SortCriteria {
    BEST_RES_MAX_SEEDERS("bestResolution"),
    BEST_SPEED("bestSpeed"),
    MAX_SEEDERS("mostSeeders"),
    ONLY_720_MAX_SEEDERS("720p"),
    DEFAULT_CRITERIA("default");

    private static final Map<String, SortCriteria> BY_LABEL = new HashMap<>();

    static {
        for (SortCriteria sc : values()) {
            BY_LABEL.put(sc.label, sc);
        }
    }

    private final String label;

    private SortCriteria(String label) {
        this.label = label;
    }

    public static SortCriteria valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
