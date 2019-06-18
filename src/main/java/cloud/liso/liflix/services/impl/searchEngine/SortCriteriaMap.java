package cloud.liso.liflix.services.impl.searchEngine;

import cloud.liso.liflix.services.api.torrent.TorrentSortCriteria;
import cloud.liso.liflix.services.impl.searchEngine.criteria.BestResolutionMaxSeedersSortCriteria;
import cloud.liso.liflix.services.impl.searchEngine.criteria.BestSpeedSortCriteria;
import cloud.liso.liflix.services.impl.searchEngine.criteria.MaxSeedersSortCriteria;
import cloud.liso.liflix.services.impl.searchEngine.criteria.OnlyHDReadyMostSeedersSortCriteria;

import java.util.HashMap;
import java.util.Map;

public class SortCriteriaMap {

    private Map<SortCriteria, TorrentSortCriteria> map;

    private TorrentSortCriteria defaultCriteria;

    public SortCriteriaMap() {
        map = new HashMap<>();
        defaultCriteria = new OnlyHDReadyMostSeedersSortCriteria();
        map.put(SortCriteria.DEFAULT_CRITERIA, defaultCriteria);
        map.put(SortCriteria.BEST_RES_MAX_SEEDERS, new BestResolutionMaxSeedersSortCriteria());
        map.put(SortCriteria.BEST_SPEED, new BestSpeedSortCriteria());
        map.put(SortCriteria.MAX_SEEDERS, new MaxSeedersSortCriteria());
    }

    public TorrentSortCriteria getOrDefault(SortCriteria criteria) {
        if (criteria == null) return defaultCriteria;
        return map.getOrDefault(criteria, defaultCriteria);
    }

}
