package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.model.torrent.Codec;
import cloud.liso.liflix.model.torrent.ReleaseType;
import cloud.liso.liflix.model.torrent.Resolution;
import cloud.liso.liflix.model.torrent.Size;
import cloud.liso.liflix.services.httpClient.DOMElement;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class ClassBasedElementsParser implements ElementsParser {
    private static final Pattern RELEASE_TYPE = Pattern.compile("(?i)((?:PPV\\.)?[HP]DTV|(?:HD)?CAM|B[DR]Rip|(?:HD-?)?TS|(?:PPV )" +
            "?WEB-?DL(?: DVDRip)?|HDRip|DVDRip|DVDRIP|CamRip|W[EB]BRip|BluRay|DvDScr|hdtv|telesync)");

    private static final Pattern CODEC = Pattern.compile("(?i)(.*((xvid)|(x264)).*)");
    private static final Pattern RESOLUTION = Pattern.compile("(?i)([0-9]{3,4}p)");

    @Override
    public TorrentElements parse(DOMElement element, Selectors selectors) {
        TorrentElements torrentElements = new AdditionalInfo(fromFieldsOfTitle(selectors, element)).invoke().getTorrentElements();
        TorrentElement<?> size = getSize(element, selectors);
        addSize(torrentElements, size);
        return torrentElements;
    }

    private void addSize(TorrentElements torrentElements, TorrentElement<?> size) {
        torrentElements.add(SelectorType.SIZE, size);
    }

    private TorrentElement<?> getSize(DOMElement element, Selectors selectors) {
        String size = selectors.getSize().apply(element);
        return new TorrentElement<>(parseSize(size));
    }

    private Size parseSize(String sizeHtml) {
        String[] sizeAndUnitMeasure = sizeHtml.split(" ");
        if (sizeAndUnitMeasure.length < 2) return Size.EMPTY;
        int size = (int) parseDouble(sizeAndUnitMeasure[0]);
        String unitMeasure = sizeAndUnitMeasure[1];
        return Size.of(size, unitMeasure);
    }

    private double parseDouble(String s) {
        double number;
        try {
            number = Double.parseDouble(s);
        } catch (RuntimeException ex) {
            number = 0.0;
        }
        return number;
    }

    private String[] fromFieldsOfTitle(Selectors selectors, DOMElement element) {
        String title = selectors.getTitle().apply(element);
        return title.split(" ");
    }

    private class AdditionalInfo {
        private String[] fields;
        private Resolution resolution;
        private Codec codec;
        private ReleaseType releaseType;

        public AdditionalInfo(String[] fields) {
            this.fields = fields;
        }

        public TorrentElement<Resolution> getResolution() {
            return new TorrentElement<>(resolution);
        }

        public TorrentElement<Codec> getCodec() {
            return new TorrentElement<>(codec);
        }

        public TorrentElement<ReleaseType> getReleaseType() {
            return new TorrentElement<>(releaseType);
        }

        public TorrentElements getTorrentElements() {
            Map<SelectorType, TorrentElement<?>> elements = new HashMap<>();
            elements.put(SelectorType.CODEC, getCodec());
            elements.put(SelectorType.RELEASE, getReleaseType());
            elements.put(SelectorType.RESOLUTION, getResolution());
            return new TorrentElements(elements);
        }

        public AdditionalInfo invoke() {
            resolution = Resolution.of("SD");
            codec = Codec.of("N/A");
            releaseType = ReleaseType.of("N/A");

            for (String field : fields) {
                if (RESOLUTION.matcher(field).matches()) {
                    resolution = Resolution.of(field);
                } else if (CODEC.matcher(field).matches()) {
                    codec = field.contains("-") ? Codec.of(field.substring(0, field.indexOf("-"))) : Codec.of(field);
                } else if (RELEASE_TYPE.matcher(field).matches()) {
                    releaseType = ReleaseType.of(field);
                }
            }
            return this;
        }
    }
}
