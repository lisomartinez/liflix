package cloud.liso.liflix.services.impl.searchEngine;

import cloud.liso.liflix.services.api.torrent.SortPolicy;
import cloud.liso.liflix.services.impl.searchEngine.criteria.BestResolutionMaxSeedersSortPolicy;
import cloud.liso.liflix.services.impl.searchEngine.criteria.BestSpeedSortPolicy;
import cloud.liso.liflix.services.impl.searchEngine.criteria.MaxSeedersSortPolicy;
import cloud.liso.liflix.services.impl.searchEngine.criteria.OnlyHDReadyMostSeedersSortPolicy;

import java.util.HashMap;
import java.util.Map;

public class SortCriteriaMap {

    private Map<SortCriteria, SortPolicy> map;

    private SortPolicy defaultCriteria;

    public SortCriteriaMap() {
        map = new HashMap<>();
        defaultCriteria = new OnlyHDReadyMostSeedersSortPolicy();
        map.put(SortCriteria.DEFAULT_CRITERIA, defaultCriteria);
        map.put(SortCriteria.BEST_RES_MAX_SEEDERS, new BestResolutionMaxSeedersSortPolicy());
        map.put(SortCriteria.BEST_SPEED, new BestSpeedSortPolicy());
        map.put(SortCriteria.MAX_SEEDERS, new MaxSeedersSortPolicy());
    }

    public SortPolicy getOrDefault(SortCriteria criteria) {
        if (criteria == null) return defaultCriteria;
        return map.getOrDefault(criteria, defaultCriteria);
    }

}
