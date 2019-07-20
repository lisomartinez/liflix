package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "episode")
public class Episode {

    @Id
    @Column(name = "episode_id")
    private int id;

    @Column(name = "tvmaze_id", nullable = false, unique = true)
    private int tvmazeId;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @Column(name = "airdate")
    private LocalDate airdate;

    @Column(name = "airtime")
    private LocalTime airtime;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "image_url")
    private String image;

    @Column(name = "tvmaze_url")
    private String tvMaze;

    //    @Column(name = "summary", nullable = false, length = 65535, columnDefinition = "TEXT")
    @Column(name = "summary", nullable = false, length = 5000)
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", foreignKey = @ForeignKey(name = "fk_episode_season"))
    private Season season;

}