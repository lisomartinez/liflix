package cloud.liso.liflix.model;

import cloud.liso.liflix.model.show.DayOfWeek;
import cloud.liso.liflix.model.show.Schedule;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static cloud.liso.liflix.model.show.DayOfWeek.THURSDAY;
import static org.assertj.core.api.Assertions.assertThat;

class ScheduleTest {

    @Test
    void staticFactoryMethod_returnsInstance() {
        Schedule schedule = Schedule.of(new ArrayList<>(Arrays.asList(DayOfWeek.of(THURSDAY))), LocalTime.of(22, 0));
        assertThat(schedule.getDays()).containsExactly(DayOfWeek.of(THURSDAY));
        assertThat(schedule.getTime()).isEqualTo(LocalTime.of(22, 0));
    }
}