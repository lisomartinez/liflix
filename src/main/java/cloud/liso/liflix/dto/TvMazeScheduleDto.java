package cloud.liso.liflix.dto;

import cloud.liso.liflix.model.show.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"time", "days"})
public class TvMazeScheduleDto {

    private List<DayOfWeek> days;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time = LocalTime.of(0, 0);

    public static TvMazeScheduleDto of(List<DayOfWeek> days, LocalTime time) {
        return new TvMazeScheduleDto(days, time);
    }

    @JsonGetter(value = "days")
    private List<String> getDaysJson() {
        return days.stream()
                .map(DayOfWeek::getDay)
                .collect(Collectors.toList());
    }

    @JsonSetter(value = "days")
    private void setDaysJson(List<String> daysStr) {
        if (daysStr.isEmpty()) {
            days = new ArrayList<>(Arrays.asList(DayOfWeek.of(DayOfWeek.NONE)));
        } else {
            days = daysStr.stream()
                    .map(DayOfWeek::of)
                    .collect(Collectors.toList());
        }
    }
}
