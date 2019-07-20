package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.model.torrent.*;
import cloud.liso.liflix.services.http_client.DOMElement;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClassBasedElementsParser implements ElementsParser {
    private static final Pattern RELEASE_TYPE = Pattern.compile("(?i)((?:PPV\\.)?[HP]DTV|(?:HD)?CAM|B[DR]Rip|(?:HD-?)?TS|(?:PPV )" +
            "?WEB-?DL(?: DVDRip)?|HDRip|DVDRip|DVDRIP|CamRip|W[EB]BRip|BluRay|DvDScr|hdtv|telesync)");

    private static final Pattern CODEC = Pattern.compile("(?i)(.*((xvid)|(x264)).*)");
    private static final Pattern RESOLUTION = Pattern.compile("(?i)([0-9]{3,4}p)");

    @Override
    public Torrent.TorrentBuilder parse(DOMElement element, Selectors selectors, Torrent.TorrentBuilder builder) {
        AdditionalInfo torrentElements = new AdditionalInfo(fromFieldsOfTitle(selectors, element)).invoke();
        builder.codec(torrentElements.getCodec());
        builder.releaseType(torrentElements.getReleaseType());
        builder.resolution(torrentElements.getResolution());

        Size size = getSize(element, selectors);
        builder.size(size);
        return builder;
    }

    private Size getSize(DOMElement element, Selectors selectors) {
        String size = applySelector(selectors, element, SelectorType.SIZE);
        return parseSize(size);
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
        String title = applySelector(selectors, element, SelectorType.TITLE);
        return title.split(" ");
    }

    private String applySelector(Selectors selectors, DOMElement element, SelectorType type) {
        return selectors.getSelectorsByType().get(type).apply(element);
    }

    @Data
    private static class AdditionalInfo {
        private String[] fields;
        private Resolution resolution;
        private Codec codec;
        private ReleaseType releaseType;

        public AdditionalInfo(String[] fields) {
            this.fields = fields;
        }

        public AdditionalInfo invoke() {
            resolution = Resolution.of("SD");
            codec = Codec.of("N/A");
            releaseType = ReleaseType.of("N/A");

            for (String field : fields) {
                if (RESOLUTION.matcher(field).matches()) {
                    resolution = Resolution.of(field);
                } else if (CODEC.matcher(field).matches()) {
                    codec = field.contains("-") ? Codec.of(field.substring(0, field.indexOf('-'))) : Codec.of(field);
                } else if (RELEASE_TYPE.matcher(field).matches()) {
                    releaseType = ReleaseType.of(field);
                }
            }
            return this;
        }
    }
}
