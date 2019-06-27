package cloud.liso.liflix.model.torrent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Size implements Comparable<Size> {
    private static final int EMPTY_SIZE = 0;
    private static final String EMPTY_UNIT = "";
    public static final Size EMPTY = new Size(EMPTY_SIZE, EMPTY_UNIT);

    @Column(name = "size_number")
    private int number;

    @Column(name = "size_unit")
    private String unit;

    public Size(int number, String unit) {
        this.number = number;
        this.unit = unit;
    }

    public static Size of(int size, String unit) {
        return new Size(size, unit);
    }

    @Override
    public int compareTo(Size o) {
        if (this.unit.equalsIgnoreCase(o.unit)) {
            return this.number - o.number;
        }

        if (this.unit.equals(EMPTY_UNIT) && o.unit.equals(EMPTY_UNIT)) return this.number - o.number;

        //arbitrarily chosen behavior
        if (this.unit.equals(EMPTY_UNIT)) return 1;
        if (o.unit.equals(EMPTY_UNIT)) return -1;


        if (this.unit.equalsIgnoreCase("GB")) {
            int size = this.number * 1024;
            if (o.unit.equalsIgnoreCase("MB")) return size - o.number;
            if (o.unit.equalsIgnoreCase("GB")) return this.number - o.number;
        } else {
            int size = o.number * 1024;
            if (o.unit.equalsIgnoreCase("MB")) return this.number - o.number;
            if (o.unit.equalsIgnoreCase("GB")) return this.number - size;
        }

        throw new IllegalArgumentException("Invalid Sizes: " + this.number + this.unit + " and " + o.number + o.unit);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size1 = (Size) o;

        if (number != size1.number) return false;
        return unit != null ? unit.equalsIgnoreCase(size1.unit) : size1.unit == null;

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

}
