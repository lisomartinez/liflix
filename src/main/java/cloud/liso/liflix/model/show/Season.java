package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "season")
public class Season {

    @Id
    @Column(name = "season_id")
    private int id;

    @Column(name = "tvmaze_id", nullable = false, unique = true)
    private int tvmazeId;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "episode_order", nullable = false)
    private int episodeOrder;

    @Column(name = "premiere_date", nullable = false)
    private LocalDate premiereDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "image_url", nullable = false)
    private String image;

    @Column(name = "tvmaze_url", nullable = false)
    private String tvMaze;

    @Column(name = "summary", nullable = false, length = 5000)
    private String summary;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "season")
    private List<Episode> episodes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", foreignKey = @ForeignKey(name = "fk_season_show"))
    private Show show;

    public void addAllEpisodes(List<Episode> episodes) {
        this.episodes.clear();
        episodes.forEach(this::addEpisode);
    }

    private void addEpisode(Episode episode) {
        episode.setSeason(this);
        this.episodes.add(episode);
    }
}