package cloud.liso.liflix.model.torrent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "codec")
public class Codec {
    @Id
    @Column(name = "type")
    private String type;

    public Codec(String type) {
        this.type = type;
    }

    public static Codec of(String codec) {
        return new Codec(codec);
    }

    public static Codec notFound() {
        return new Codec("N/A");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Codec codec1 = (Codec) o;

        return type.equalsIgnoreCase(codec1.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

}
