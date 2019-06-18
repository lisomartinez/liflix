package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "show_genre",
            joinColumns = @JoinColumn(name = "genres_genre_id"),
            foreignKey = @ForeignKey(name = "fk_show_genre_show")
    )
    private List<Show> shows;

    public Genre(String genre) {
        this.name = genre;
    }

    public Genre(String name, List<Show> shows) {
        this.name = name;
        this.shows = shows;
    }

    public static Genre of(String genre) {
        return new Genre(genre);
    }

    public static Genre of(String genre, List<Show> shows) {
        return new Genre(genre, shows);
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public void removeShow(Show show) {
        shows.remove(show);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}