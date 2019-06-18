package cloud.liso.liflix.torrentfinder.model;

import cloud.liso.liflix.model.torrent.Codec;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodecTest {

    @Test
    void equalsIgnoreCase() {
        assertThat(Codec.of("codec")).isEqualTo(Codec.of("CODEC"));
    }
}