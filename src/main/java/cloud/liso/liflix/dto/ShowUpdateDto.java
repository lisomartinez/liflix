package cloud.liso.liflix.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize(using = ShowUpdateDeserializer.class)
public class ShowUpdateDto {
    private Map<Integer, LocalDateTime> shows;
}
