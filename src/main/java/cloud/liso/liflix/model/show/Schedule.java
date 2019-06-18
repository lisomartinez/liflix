package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int id;

    @ManyToMany
    @JoinTable(name = "schedule_days_of_week",
            joinColumns = @JoinColumn(name = "schedule_id"),
            foreignKey = @ForeignKey(name = "fk_schedule_days_of_week_schedule"),
            inverseJoinColumns = @JoinColumn(name = "days_of_week_id"),
            inverseForeignKey = @ForeignKey(name = "fk_schedule_days_of_week_days_of_week")
    )
    private List<DayOfWeek> days;


    @Column(name = "time_of_show")
    private LocalTime time;

    public Schedule(List<DayOfWeek> days, LocalTime time) {
        this.days = days;
        this.time = time;
    }

    public static Schedule of(List<DayOfWeek> days, LocalTime time) {
        return new Schedule(days, time);
    }

}