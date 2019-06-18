package cloud.liso.liflix.model.show;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "days_of_week")
@Data
@NoArgsConstructor
public class DayOfWeek {
    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";
    public static final String SATURDAY = "Saturday";
    public static final String SUNDAY = "Sunday";
    public static final String NONE = "N/A";

    @Id
    @Column(name = "day_of_week_id")
    private int id;

    @Column(name = "day", nullable = false)
    private String day;

    public DayOfWeek(String day) {
        this.day = day;
    }

    public static DayOfWeek of(String day) {
        return new DayOfWeek(day);
    }

    @Override
    public String toString() {
        return day;
    }
}
