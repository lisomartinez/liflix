package cloud.liso.liflix.services.torrent.sortpolicies;

import cloud.liso.liflix.services.torrent.sortpolicies.policies.BestResolutionMaxSeedersSortPolicy;
import cloud.liso.liflix.services.torrent.sortpolicies.policies.MaxSeedersBestResolutionSortPolicy;
import cloud.liso.liflix.services.torrent.sortpolicies.policies.MinSizeAndMostSeedersPolicy;
import cloud.liso.liflix.services.torrent.sortpolicies.policies.OnlyHDReadyMostSeedersSortPolicy;

import java.util.HashMap;
import java.util.Map;

public class SortPolicies {

    private Map<SortPolicyId, SortPolicy> map;

    private SortPolicy defaultCriteria;

    public SortPolicies() {
        map = new HashMap<>();
        defaultCriteria = new OnlyHDReadyMostSeedersSortPolicy();
        map.put(SortPolicyId.DEFAULT_CRITERIA, defaultCriteria);
        map.put(SortPolicyId.BEST_RES_MAX_SEEDERS, new BestResolutionMaxSeedersSortPolicy());
        map.put(SortPolicyId.BEST_SPEED, new MinSizeAndMostSeedersPolicy());
        map.put(SortPolicyId.MAX_SEEDERS, new MaxSeedersBestResolutionSortPolicy());
    }

    public SortPolicy getOrDefault(SortPolicyId criteria) {
        if (criteria == null) return defaultCriteria;
        return map.getOrDefault(criteria, defaultCriteria);
    }

}
