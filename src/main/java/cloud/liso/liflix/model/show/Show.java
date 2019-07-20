package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shows")
public class Show {

    @Id
    @Column(name = "show_id")
    private int id;

    @Column(name = "tvmaze_id", nullable = false, unique = true)
    private int tvmazeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "language", nullable = false)
    private String language;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "show_genre",
            joinColumns = @JoinColumn(name = "shows_show_id"),
            foreignKey = @ForeignKey(name = "fk_show_genre_genre")
    )
    private List<Genre> genres;


    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "runtime", nullable = false)
    private int runtime;

    @Column(name = "premiered", nullable = false)
    private LocalDate premiered;

    @Column(name = "official_site_url", nullable = false)
    private String officialSite;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "schedule_id", foreignKey = @ForeignKey(name = "fk_show_schedule_id"),
            nullable = false,
            unique = true)
    private Schedule schedule;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "imdb_url", nullable = false)
    private String imdb;

    @Column(name = "tv_maze_url", nullable = false)
    private String tvMaze;

    @Column(name = "image_url", nullable = false)
    private String image;

    @Column(name = "summary", nullable = false, length = 5000)
    private String summary;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "show")
    private List<Season> seasons;

    public void addAllSeasons(List<Season> seasons) {
        this.seasons.clear();
        seasons.forEach(this::addSeason);
    }

    public void addSeason(Season season) {
        seasons.add(season);
        season.setShow(this);
    }

    public void removeSeason(Season season) {
        seasons.remove(season);
        season.setShow(null);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.addShow(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.removeShow(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return id == show.id &&
                name.equals(show.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
    
