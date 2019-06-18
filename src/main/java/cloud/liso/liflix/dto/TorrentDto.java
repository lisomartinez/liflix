package cloud.liso.liflix.dto;

import cloud.liso.liflix.model.torrent.Codec;
import cloud.liso.liflix.model.torrent.ReleaseType;
import cloud.liso.liflix.model.torrent.Resolution;
import cloud.liso.liflix.model.torrent.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorrentDto {
    private String title;
    private String magnetLink;
    private Resolution resolution;
    private Codec codec;
    private ReleaseType releaseType;
    private Size size;
    private int seeders;
    private int leechers;

    public static TorrentDtoBuilder builder() {
        return new TorrentDtoBuilder();
    }

    public static class TorrentDtoBuilder {
        private String title;
        private String magnetLink;
        private ReleaseType relaseType;
        private Codec codec;
        private Resolution resolution;
        private Size size;
        private int seeders;
        private int leechers;

        public TorrentDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public TorrentDtoBuilder withMagnetLink(String magnetLink) {
            this.magnetLink = magnetLink;
            return this;
        }

        public TorrentDtoBuilder withReleaseType(ReleaseType relaseType) {
            this.relaseType = relaseType;
            return this;
        }

        public TorrentDtoBuilder withResolution(Resolution resolution) {
            this.resolution = resolution;
            return this;
        }

        public TorrentDtoBuilder withCodec(Codec codec) {
            this.codec = codec;
            return this;
        }

        public TorrentDtoBuilder withSize(Size size) {
            this.size = size;
            return this;
        }

        public TorrentDtoBuilder withSeeders(int seeders) {
            this.seeders = seeders;
            return this;
        }

        public TorrentDtoBuilder withLeechers(int leechers) {
            this.leechers = leechers;
            return this;
        }

        public TorrentDto build() {
            return new TorrentDto(title, magnetLink, resolution, codec, relaseType, size, seeders, leechers);
        }
    }
}
