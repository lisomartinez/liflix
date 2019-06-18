package cloud.liso.liflix.model.torrent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "torrent_list")
public class TorrentList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "torrent_list_id")
    private int id;

    @Column(name = "show_id")
    private int showId;

    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "episode_number")
    private int episodeNumber;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "torrentList", fetch = FetchType.EAGER)
    private List<Torrent> torrents;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public boolean areTorrentsPresent() {
        return !this.torrents.isEmpty();
    }

    public Torrent getFirstTorrent() {
        return this.torrents.get(0);
    }
}
