package cloud.liso.liflix.services.torrent.sortPolicies;

import cloud.liso.liflix.services.torrent.sortPolicies.policies.BestResolutionMaxSeedersSortPolicy;
import cloud.liso.liflix.services.torrent.sortPolicies.policies.BestSpeedSortPolicy;
import cloud.liso.liflix.services.torrent.sortPolicies.policies.MaxSeedersSortPolicy;
import cloud.liso.liflix.services.torrent.sortPolicies.policies.OnlyHDReadyMostSeedersSortPolicy;

import java.util.HashMap;
import java.util.Map;

public class SortPolicies {

    private Map<SortCriteria, SortPolicy> map;

    private SortPolicy defaultCriteria;

    public SortPolicies() {
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
