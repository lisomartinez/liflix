package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.model.torrent.Codec;
import cloud.liso.liflix.model.torrent.ReleaseType;
import cloud.liso.liflix.model.torrent.Resolution;
import cloud.liso.liflix.model.torrent.Size;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TorrentElements {
    private Map<SelectorType, TorrentElement<?>> elements;

    public TorrentElements(Map<SelectorType, TorrentElement<?>> elements) {
        this.elements = elements;
    }

    public TorrentElements concat(TorrentElements other) {
        this.elements = Stream.concat(this.elements.entrySet().stream(), other.elements.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return this;
    }


    public String getTitle() {
        return (String) this.elements.get(SelectorType.TITLE).getContent();
    }

    public String getMagnet() {
        return (String) this.elements.get(SelectorType.MAGNET).getContent();
    }

    public Resolution getResolution() {
        return (Resolution) this.elements.get(SelectorType.RESOLUTION).getContent();
    }

    public Codec getCodec() {
        return (Codec) this.elements.get(SelectorType.CODEC).getContent();
    }

    public ReleaseType getReleaseType() {
        return (ReleaseType) this.elements.get(SelectorType.RELEASE).getContent();
    }

    public Size getSize() {
        return (Size) this.elements.get(SelectorType.SIZE).getContent();
    }

    public void add(SelectorType selectorType, TorrentElement<?> element) {
        this.elements.put(selectorType, element);
    }

    public int getSeeders() {
        String seeders = (String) this.elements.get(SelectorType.SEEDERS).getContent();
        return parseInteger(seeders);
    }

    private int parseInteger(String st) {
        int number;
        try {
            number = Integer.parseInt(st);
        } catch (RuntimeException ex) {
            number = 0;
        }
        return number;
    }

    public int getLeedchers() {
        String leechers = (String) this.elements.get(SelectorType.SEEDERS).getContent();
        return parseInteger(leechers);
    }
}
