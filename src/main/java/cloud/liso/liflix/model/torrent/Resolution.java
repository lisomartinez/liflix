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
@Table(name = "resolution")
public class Resolution implements Comparable<Resolution> {
    public static final Resolution RESOLUTION_720P = Resolution.of("720p");

    @Id
    @Column(name = "type")
    private String type;

    public Resolution(String resolution) {
        this.type = resolution;
    }


    public static Resolution of(String resolution) {
        return new Resolution(resolution);
    }

    @Override
    public int compareTo(Resolution other) {
        if (this.type.equalsIgnoreCase(other.type)) return 0;

        if (this.type.equalsIgnoreCase("1080p") && (other.type.equalsIgnoreCase("SD"))) {
            return 2;
        } else if (this.type.equalsIgnoreCase("1080p") && (other.type.equalsIgnoreCase("480p"))) {
            return 2;
        } else if (this.type.equalsIgnoreCase("1080p") && (other.type.equalsIgnoreCase("720p"))) {
            return 1;
        } else if (this.type.equalsIgnoreCase("720p") && (other.type.equalsIgnoreCase("SD"))) {
            return 1;
        } else if (this.type.equalsIgnoreCase("720p") && (other.type.equalsIgnoreCase("480p"))) {
            return 1;
        } else if (this.type.equalsIgnoreCase("720p") && (other.type.equalsIgnoreCase("1080p"))) {
            return -1;
        } else if (this.type.equalsIgnoreCase("SD") && (other.type.equalsIgnoreCase("1080p"))) {
            return -2;
        } else if (this.type.equalsIgnoreCase("SD") && (other.type.equalsIgnoreCase("720p"))) {
            return -1;
        } else if (this.type.equalsIgnoreCase("SD") && (other.type.equalsIgnoreCase("480p"))) {
            return 0;
        } else if (this.type.equalsIgnoreCase("480p") && (other.type.equalsIgnoreCase("1080p"))) {
            return -2;
        } else if (this.type.equalsIgnoreCase("480p") && (other.type.equalsIgnoreCase("720p"))) {
            return -1;
        } else if (this.type.equalsIgnoreCase("480p") && (other.type.equalsIgnoreCase("SD"))) {
            return 0;
        }


        throw new IllegalArgumentException("Invalid type comparison between: " + this.type + " and " + other.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resolution that = (Resolution) o;

        return type.equalsIgnoreCase(that.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
