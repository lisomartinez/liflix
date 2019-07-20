package cloud.liso.liflix.services.torrent.sortpolicies;

import cloud.liso.liflix.model.torrent.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SortPoliciesTest {
    private SortPolicies sortPolicies;

    @BeforeEach
    void setUp() {
        sortPolicies = new SortPolicies();
    }

    @Test
    void sortByDefaultPolicy_ListOfTorrents_ShouldReturnsSortedListOfTorrents() {
        List<Torrent> torrents = createListOfTorrents();
        List<Torrent> expected = createOnlyHRReadyMostSeeders();
        SortPolicy defaultPolicy = sortPolicies.getOrDefault(SortPolicyId.DEFAULT_CRITERIA);
        List<Torrent> sortedTorrents = defaultPolicy.sort(torrents);
        assertThat(sortedTorrents).containsExactlyElementsOf(expected);
    }

    private List<Torrent> createListOfTorrents() {
        List<Torrent> list = new LinkedList<>();
        Torrent t1 = Torrent.builder()
                .id(1)
                .title("t1")
                .magnetLink("magnet1")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(1, "GB"))
                .seeders(11)
                .leechers(1)
                .torrentList(new TorrentList())
                .build();
        Torrent t2 = Torrent.builder()
                .id(2)
                .title("t2")
                .magnetLink("magnet2")
                .resolution(Resolution.of(Resolution.RESOLUTION_480P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(2, "GB"))
                .seeders(22)
                .leechers(2)
                .torrentList(new TorrentList())
                .build();
        Torrent t3 = Torrent.builder()
                .id(3)
                .title("t3")
                .magnetLink("magnet3")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(500, "MB"))
                .seeders(5)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t4 = Torrent.builder()
                .id(4)
                .title("t4")
                .magnetLink("magnet4")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(510)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();

        Torrent t5 = Torrent.builder()
                .id(5)
                .title("t5")
                .magnetLink("magnet5")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(15)
                .leechers(10)
                .torrentList(new TorrentList())
                .build();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        return list;
    }

    private List<Torrent> createOnlyHRReadyMostSeeders() {
        List<Torrent> list = new LinkedList<>();
        Torrent t1 = Torrent.builder()
                .id(1)
                .title("t1")
                .magnetLink("magnet1")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(1, "GB"))
                .seeders(11)
                .leechers(1)
                .torrentList(new TorrentList())
                .build();
        Torrent t4 = Torrent.builder()
                .id(4)
                .title("t4")
                .magnetLink("magnet4")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(510)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        list.add(t4);
        list.add(t1);
        return list;
    }


    @Test
    void sortByMinSizeAndMostSeeders_ListOfTorrents_ShouldReturnSortedListOfTorrents() {
        List<Torrent> torrents = createListOfTorrents();
        List<Torrent> expected = createMinSizeAndMostSeeders();
        SortPolicy defaultPolicy = sortPolicies.getOrDefault(SortPolicyId.BEST_SPEED);
        List<Torrent> sortedTorrents = defaultPolicy.sort(torrents);
        assertThat(sortedTorrents).containsExactlyElementsOf(expected);
    }

    private List<Torrent> createMinSizeAndMostSeeders() {
        List<Torrent> list = new LinkedList<>();
        Torrent t1 = Torrent.builder()
                .id(1)
                .title("t1")
                .magnetLink("magnet1")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(1, "GB"))
                .seeders(11)
                .leechers(1)
                .torrentList(new TorrentList())
                .build();
        Torrent t2 = Torrent.builder()
                .id(2)
                .title("t2")
                .magnetLink("magnet2")
                .resolution(Resolution.of(Resolution.RESOLUTION_480P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(2, "GB"))
                .seeders(22)
                .leechers(2)
                .torrentList(new TorrentList())
                .build();
        Torrent t3 = Torrent.builder()
                .id(3)
                .title("t3")
                .magnetLink("magnet3")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(500, "MB"))
                .seeders(5)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t4 = Torrent.builder()
                .id(4)
                .title("t4")
                .magnetLink("magnet4")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(510)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t5 = Torrent.builder()
                .id(5)
                .title("t5")
                .magnetLink("magnet5")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(15)
                .leechers(10)
                .torrentList(new TorrentList())
                .build();
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t1);
        list.add(t2);
        return list;
    }

    @Test
    void sortByBestResolutionMaxSeeders_ListOfTorrents_ShouldReturnSortedListOfTorrents() {
        List<Torrent> torrents = createListOfTorrents();
        List<Torrent> expected = createBestResolutionMaxSeeders();
        SortPolicy defaultPolicy = sortPolicies.getOrDefault(SortPolicyId.BEST_RES_MAX_SEEDERS);
        List<Torrent> sortedTorrents = defaultPolicy.sort(torrents);
        assertThat(sortedTorrents).containsExactlyElementsOf(expected);
    }

    private List<Torrent> createBestResolutionMaxSeeders() {
        List<Torrent> list = new LinkedList<>();
        Torrent t1 = Torrent.builder()
                .id(1)
                .title("t1")
                .magnetLink("magnet1")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(1, "GB"))
                .seeders(11)
                .leechers(1)
                .torrentList(new TorrentList())
                .build();
        Torrent t2 = Torrent.builder()
                .id(2)
                .title("t2")
                .magnetLink("magnet2")
                .resolution(Resolution.of(Resolution.RESOLUTION_480P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(2, "GB"))
                .seeders(22)
                .leechers(2)
                .torrentList(new TorrentList())
                .build();
        Torrent t3 = Torrent.builder()
                .id(3)
                .title("t3")
                .magnetLink("magnet3")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(500, "MB"))
                .seeders(5)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t4 = Torrent.builder()
                .id(4)
                .title("t4")
                .magnetLink("magnet4")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(510)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t5 = Torrent.builder()
                .id(5)
                .title("t5")
                .magnetLink("magnet5")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(15)
                .leechers(10)
                .torrentList(new TorrentList())
                .build();
        list.add(t5);
        list.add(t3);
        list.add(t4);
        list.add(t1);
        list.add(t2);
        return list;
    }

    @Test
    void sortByMaxSeeders_ListOfTorrents_ShouldReturnSortedListOfTorrents() {
        List<Torrent> torrents = createListOfTorrents();
        List<Torrent> expected = createMaxSeeders();
        SortPolicy defaultPolicy = sortPolicies.getOrDefault(SortPolicyId.MAX_SEEDERS);
        List<Torrent> sortedTorrents = defaultPolicy.sort(torrents);
        assertThat(sortedTorrents).containsExactlyElementsOf(expected);
    }

    private List<Torrent> createMaxSeeders() {
        List<Torrent> list = new LinkedList<>();
        Torrent t1 = Torrent.builder()
                .id(1)
                .title("t1")
                .magnetLink("magnet1")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(1, "GB"))
                .seeders(11)
                .leechers(1)
                .torrentList(new TorrentList())
                .build();
        Torrent t2 = Torrent.builder()
                .id(2)
                .title("t2")
                .magnetLink("magnet2")
                .resolution(Resolution.of(Resolution.RESOLUTION_480P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(2, "GB"))
                .seeders(22)
                .leechers(2)
                .torrentList(new TorrentList())
                .build();
        Torrent t3 = Torrent.builder()
                .id(3)
                .title("t3")
                .magnetLink("magnet3")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(500, "MB"))
                .seeders(5)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t4 = Torrent.builder()
                .id(4)
                .title("t4")
                .magnetLink("magnet4")
                .resolution(Resolution.RESOLUTION_OF_720P)
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(510)
                .leechers(100)
                .torrentList(new TorrentList())
                .build();
        Torrent t5 = Torrent.builder()
                .id(5)
                .title("t5")
                .magnetLink("magnet5")
                .resolution(Resolution.of(Resolution.RESOLUTION_1080P))
                .codec(Codec.notFound())
                .releaseType(ReleaseType.notFound())
                .size(Size.of(520, "MB"))
                .seeders(15)
                .leechers(10)
                .torrentList(new TorrentList())
                .build();
        list.add(t4);
        list.add(t2);
        list.add(t5);
        list.add(t1);
        list.add(t3);
        return list;
    }
}