package cloud.liso.liflix.torrentfinder.model;

import cloud.liso.liflix.model.torrent.Resolution;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResolutionTest {

    @Test
    void compareTo() {
        Resolution upperCase1080p = Resolution.of("1080P");
        Resolution lowerCase1080p = Resolution.of("1080p");

        Resolution upperCase720p = Resolution.of("720P");
        Resolution lowerCase720p = Resolution.of("720p");

        Resolution upperCaseSD = Resolution.of("SD");
        Resolution lowerCaseSD = Resolution.of("sd");

        assertThat(upperCase1080p.compareTo(lowerCase1080p)).isEqualTo(0);
        assertThat(upperCase1080p.compareTo(lowerCase720p)).isEqualTo(1);
        assertThat(upperCase1080p.compareTo(lowerCaseSD)).isEqualTo(2);
        assertThat(upperCase1080p.compareTo(upperCase720p)).isEqualTo(1);
        assertThat(upperCase1080p.compareTo(upperCaseSD)).isEqualTo(2);

        assertThat(lowerCase1080p.compareTo(upperCase1080p)).isEqualTo(0);
        assertThat(lowerCase1080p.compareTo(lowerCase720p)).isEqualTo(1);
        assertThat(lowerCase1080p.compareTo(lowerCaseSD)).isEqualTo(2);
        assertThat(lowerCase1080p.compareTo(upperCase720p)).isEqualTo(1);
        assertThat(lowerCase1080p.compareTo(upperCaseSD)).isEqualTo(2);

        assertThat(upperCase720p.compareTo(lowerCase720p)).isEqualTo(0);

        assertThat(upperCase720p.compareTo(upperCase1080p)).isEqualTo(-1);
        assertThat(upperCase720p.compareTo(lowerCase1080p)).isEqualTo(-1);
        assertThat(upperCase720p.compareTo(upperCaseSD)).isEqualTo(1);
        assertThat(upperCase720p.compareTo(lowerCaseSD)).isEqualTo(1);

        assertThat(lowerCase720p.compareTo(upperCase1080p)).isEqualTo(-1);
        assertThat(lowerCase720p.compareTo(lowerCase1080p)).isEqualTo(-1);
        assertThat(lowerCase720p.compareTo(upperCaseSD)).isEqualTo(1);
        assertThat(lowerCase720p.compareTo(lowerCaseSD)).isEqualTo(1);

        assertThat(upperCaseSD.compareTo(lowerCaseSD)).isEqualTo(0);
        assertThat(upperCaseSD.compareTo(upperCase1080p)).isEqualTo(-2);
        assertThat(upperCaseSD.compareTo(lowerCase1080p)).isEqualTo(-2);
        assertThat(upperCaseSD.compareTo(upperCase720p)).isEqualTo(-1);
        assertThat(upperCaseSD.compareTo(upperCase720p)).isEqualTo(-1);

        assertThat(lowerCaseSD.compareTo(upperCaseSD)).isEqualTo(0);
        assertThat(lowerCaseSD.compareTo(upperCase1080p)).isEqualTo(-2);
        assertThat(lowerCaseSD.compareTo(lowerCase1080p)).isEqualTo(-2);
        assertThat(lowerCaseSD.compareTo(upperCase720p)).isEqualTo(-1);
        assertThat(lowerCaseSD.compareTo(upperCase720p)).isEqualTo(-1);
    }

    @Test
    void equals() {
        Resolution upperCase1080p = Resolution.of("1080P");
        Resolution lowerCase1080p = Resolution.of("1080p");

        Resolution upperCase720p = Resolution.of("720P");
        Resolution lowerCase720p = Resolution.of("720p");

        Resolution upperCaseSD = Resolution.of("SD");
        Resolution lowerCaseSD = Resolution.of("sd");

        assertThat(upperCase1080p).isEqualTo(lowerCase1080p);
        assertThat(upperCase1080p).isNotEqualTo(lowerCase720p);
        assertThat(upperCase1080p).isNotEqualTo(upperCase720p);
        assertThat(upperCase1080p).isNotEqualTo(lowerCaseSD);
        assertThat(upperCase1080p).isNotEqualTo(upperCaseSD);

        assertThat(lowerCase1080p).isNotEqualTo(lowerCase720p);
        assertThat(lowerCase1080p).isNotEqualTo(upperCase720p);
        assertThat(lowerCase1080p).isNotEqualTo(lowerCaseSD);
        assertThat(lowerCase1080p).isNotEqualTo(upperCaseSD);

        assertThat(upperCase720p).isEqualTo(lowerCase720p);
        assertThat(upperCase720p).isNotEqualTo(lowerCaseSD);
        assertThat(upperCase720p).isNotEqualTo(upperCaseSD);
        assertThat(lowerCase720p).isNotEqualTo(lowerCaseSD);
        assertThat(lowerCase720p).isNotEqualTo(upperCaseSD);
    }
}