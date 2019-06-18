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
@Table(name = "release_type")
public class ReleaseType {

    @Id
    @Column(name = "type")
    private String type;

    public ReleaseType(String type) {
        this.type = type;
    }

    public static ReleaseType of(String relaseType) {
        return new ReleaseType(relaseType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleaseType that = (ReleaseType) o;

        return type.equalsIgnoreCase(that.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
