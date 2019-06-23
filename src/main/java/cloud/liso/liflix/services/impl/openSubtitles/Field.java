package cloud.liso.liflix.services.impl.openSubtitles;

import lombok.Data;

import java.util.Objects;

@Data
final class Field {
    final int position;
    final String content;

    private Field(int position, String content) {
        this.position = position;
        this.content = content;
    }

    static Field of(int position, String content) {
        return new Field(position, content);
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return position == field.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
