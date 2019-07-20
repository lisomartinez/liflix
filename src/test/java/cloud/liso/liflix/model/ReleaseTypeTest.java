package cloud.liso.liflix.model;

import cloud.liso.liflix.model.torrent.ReleaseType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReleaseTypeTest {

    @Test
    void equals() {
        assertThat(ReleaseType.of("release")).isEqualTo(ReleaseType.of("RELEASE"));
    }
}