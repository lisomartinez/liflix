package cloud.liso.liflix.model;

import cloud.liso.liflix.model.torrent.Size;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SizeTest {

    @Test
    void compareTo() {
        Size mb1000 = Size.of(1000, "MB");
        Size mb1025 = Size.of(1025, "MB");

        Size gb1 = Size.of(1, "GB");

        Size gb2 = Size.of(2, "GB");


        assertThat(mb1000.compareTo(mb1000)).isEqualTo(0);
        assertThat(mb1000.compareTo(mb1025)).isLessThanOrEqualTo(-1);
        assertThat(mb1000.compareTo(gb1)).isLessThanOrEqualTo(-1);
        assertThat(mb1000.compareTo(gb2)).isLessThanOrEqualTo(-1);

        assertThat(mb1025.compareTo(mb1025)).isEqualTo(0);
        assertThat(mb1025.compareTo(mb1000)).isGreaterThanOrEqualTo(1);
        assertThat(mb1025.compareTo(gb1)).isGreaterThanOrEqualTo(1);
        assertThat(mb1025.compareTo(gb2)).isLessThanOrEqualTo(-1);

        assertThat(gb1.compareTo(mb1025)).isLessThanOrEqualTo(-1);
        assertThat(gb1.compareTo(mb1000)).isGreaterThanOrEqualTo(1);
        assertThat(gb1.compareTo(gb1)).isEqualTo(0);
        assertThat(gb1.compareTo(gb2)).isLessThanOrEqualTo(-1);


        assertThat(gb2.compareTo(mb1025)).isGreaterThanOrEqualTo(1);
        assertThat(gb2.compareTo(mb1000)).isGreaterThanOrEqualTo(1);
        assertThat(gb2.compareTo(gb1)).isGreaterThanOrEqualTo(1);
        assertThat(gb2.compareTo(gb2)).isEqualTo(0);

    }

    @Test
    void equals1() {
        Size mbUpper = Size.of(1, "MB");
        Size mbLower = Size.of(1, "mb");
        Size gbUpper = Size.of(1, "GB");
        Size gbLower = Size.of(1, "gb");

        assertThat(mbLower).isEqualTo(mbUpper);
        assertThat(mbUpper).isEqualTo(mbLower);

        assertThat(mbUpper).isNotEqualTo(gbLower);
        assertThat(mbUpper).isNotEqualTo(gbUpper);

        assertThat(mbLower).isNotEqualTo(gbLower);
        assertThat(mbLower).isNotEqualTo(gbUpper);


        assertThat(gbLower).isEqualTo(gbUpper);
        assertThat(mbUpper).isEqualTo(mbLower);

        assertThat(gbUpper).isNotEqualTo(mbLower);
        assertThat(gbUpper).isNotEqualTo(mbUpper);

        assertThat(gbLower).isNotEqualTo(mbLower);
        assertThat(gbLower).isNotEqualTo(mbUpper);
    }

}