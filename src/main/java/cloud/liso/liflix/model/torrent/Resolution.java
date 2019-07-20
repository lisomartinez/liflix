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
    public static final Resolution RESOLUTION_OF_720P = Resolution.of("720p");
    public static final String RESOLUTION_720P = "720p";
    public static final String RESOLUTION_SD = "SD";
    public static final String RESOLUTION_480P = "480p";
    public static final String RESOLUTION_1080P = "1080p";

    @Id
    @Column(name = "type")
    private String type;

    public Resolution(String resolution) {
        this.type = resolution;
    }


    public static Resolution of(String resolution) {
        return new Resolution(resolution);
    }

    public static Resolution notFound() {
        return new Resolution("RESOLUTION_SD");
    }

    @Override
    public int compareTo(Resolution other) {
        if (this.type.equalsIgnoreCase(other.type)) return 0;

        if (this.type.equalsIgnoreCase(RESOLUTION_1080P) && (other.type.equalsIgnoreCase(RESOLUTION_SD))) {
            return 2;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_1080P) && (other.type.equalsIgnoreCase(RESOLUTION_480P))) {
            return 2;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_1080P) && (other.type.equalsIgnoreCase(RESOLUTION_720P))) {
            return 1;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_720P) && (other.type.equalsIgnoreCase(RESOLUTION_SD))) {
            return 1;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_720P) && (other.type.equalsIgnoreCase(RESOLUTION_480P))) {
            return 1;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_720P) && (other.type.equalsIgnoreCase(RESOLUTION_1080P))) {
            return -1;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_SD) && (other.type.equalsIgnoreCase(RESOLUTION_1080P))) {
            return -2;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_SD) && (other.type.equalsIgnoreCase(RESOLUTION_720P))) {
            return -1;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_SD) && (other.type.equalsIgnoreCase(RESOLUTION_480P))) {
            return 0;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_480P) && (other.type.equalsIgnoreCase(RESOLUTION_1080P))) {
            return -2;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_480P) && (other.type.equalsIgnoreCase(RESOLUTION_720P))) {
            return -1;
        } else if (this.type.equalsIgnoreCase(RESOLUTION_480P) && (other.type.equalsIgnoreCase(RESOLUTION_SD))) {
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
