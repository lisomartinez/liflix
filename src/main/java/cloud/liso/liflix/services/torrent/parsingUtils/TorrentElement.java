package cloud.liso.liflix.services.torrent.parsingUtils;

import java.util.Objects;

public class TorrentElement<T> {
    private final T content;

    public TorrentElement(T content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorrentElement<?> that = (TorrentElement<?>) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    public T getContent() {
        return content;
    }
}
