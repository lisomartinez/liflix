package cloud.liso.liflix.model.torrent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "torrent")
public class Torrent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "torrent_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "magnet")
    private String magnetLink;

    @OneToOne
    @JoinColumn(name = "resolution_id", foreignKey = @ForeignKey(name = "fk_torrent_resolution"))
    private Resolution resolution;

    @OneToOne
    @JoinColumn(name = "codec_id", foreignKey = @ForeignKey(name = "fk_torrent_codec"))
    private Codec codec;

    @OneToOne
    @JoinColumn(name = "release_id", foreignKey = @ForeignKey(name = "fk_torrent_release"))
    private ReleaseType releaseType;

    @Embedded
    private Size size;

    @Column(name = "seeders")
    private int seeders;

    @Column(name = "leechers")
    private int leechers;

    @ManyToOne
    @JoinColumn(name = "torrent_list_id", foreignKey = @ForeignKey(name = "fk_torrent_torrent_list"))
    private TorrentList torrentList;

    public static Torrent empty() {
        return new Torrent();
    }

}
