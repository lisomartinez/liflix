package cloud.liso.liflix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    private String name;

    public static GenreDto of(String name) {
        return new GenreDto(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
