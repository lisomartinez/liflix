package cloud.liso.liflix.services.impl.torrent.zooqle.parsers;

import cloud.liso.liflix.exceptions.EpisodeParsingException;
import cloud.liso.liflix.model.torrent.*;
import cloud.liso.liflix.services.api.httpClient.DOMDocument;
import cloud.liso.liflix.services.api.httpClient.DOMElement;
import cloud.liso.liflix.services.api.torrent.EpisodeParser;
import cloud.liso.liflix.services.impl.torrent.zooqle.selectors.element.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ZooqleEpisodeParser implements EpisodeParser {

    private static final Pattern RELEASE_TYPE = Pattern.compile("(?i)((?:PPV\\.)?[HP]DTV|(?:HD)?CAM|B[DR]Rip|(?:HD-?)?TS|(?:PPV )" +
            "?WEB-?DL(?: DVDRip)?|HDRip|DVDRip|DVDRIP|CamRip|W[EB]BRip|BluRay|DvDScr|hdtv|telesync)");

    private static final Pattern CODEC = Pattern.compile("(?i)(.*((xvid)|(x264)).*)");
    private static final Pattern RESOLUTION = Pattern.compile("(?i)([0-9]{3,4}p)");

    private ZoogleDocumentSelector documentSelector;

    private ZoogleTitleElementSelector titleCriteria;

    private ZoogleSizeElementSelector sizeCriteria;

    private ZoogleMagnetLinkElementSelector magnetLinkCriteria;

    private ZoogleLeechersElementSelector leechersCriteria;

    private ZoogleSeederElementSelector seederCriteria;


    @Autowired
    public ZooqleEpisodeParser(ZoogleDocumentSelector documentSelector, ZoogleTitleElementSelector titleCriteria,
                               ZoogleSizeElementSelector sizeCriteria, ZoogleMagnetLinkElementSelector magnetLinkCriteria,
                               ZoogleLeechersElementSelector leechersCriteria, ZoogleSeederElementSelector seederCriteria) {
        this.documentSelector = documentSelector;
        this.titleCriteria = titleCriteria;
        this.sizeCriteria = sizeCriteria;
        this.magnetLinkCriteria = magnetLinkCriteria;
        this.leechersCriteria = leechersCriteria;
        this.seederCriteria = seederCriteria;
    }

    @Override
    public List<Torrent> parseDOMDocument(DOMDocument document) {
        try {
            List<DOMElement> elements = documentSelector.apply(document);
            return elements.stream().map(this::parse).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new EpisodeParsingException(ex.getMessage());
        }
    }

    private Torrent parse(DOMElement element) {
        String title = titleCriteria.apply(element);
        String magnetLink = magnetLinkCriteria.apply(element);
        TorrentAdditionalData data = parseData(title);
        String size = sizeCriteria.apply(element);
        String seeders = seederCriteria.apply(element);
        String leechers = leechersCriteria.apply(element);
        return Torrent.builder()
                .title(title)
                .magnetLink(magnetLink)
                .resolution(data.getResolution())
                .codec(data.getCodec())
                .releaseType(data.getReleaseType())
                .size(parseSize(size))
                .seeders(parseSeeders(seeders))
                .leechers(parseLeechers(leechers))
                .build();
    }

    private TorrentAdditionalData parseData(String title) {
        String[] fields = title.split(" ");
        Resolution resolution = Resolution.of("SD");
        Codec codec = Codec.of("N/A");
        ReleaseType releaseType = ReleaseType.of("N/A");
        for (String field : fields) {
            if (RESOLUTION.matcher(field).matches()) {
                //TODO: Revisar
                resolution = Resolution.of(field);
            } else if (CODEC.matcher(field).matches()) {
                codec = field.contains("-") ? Codec.of(field.substring(0, field.indexOf("-"))) : Codec.of(field);
            } else if (RELEASE_TYPE.matcher(field).matches()) {
                releaseType = ReleaseType.of(field);
            }
        }
        return new TorrentAdditionalData(resolution, codec, releaseType);
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

    private int parseLeechers(String leechers) {
        return parseInteger(leechers);
    }

    private Size parseSize(String sizeHtml) {
        String[] sizeAndUnitMeasure = sizeHtml.split(" ");
        if (sizeAndUnitMeasure.length < 2) return Size.EMPTY;
        int size = (int) parseDouble(sizeAndUnitMeasure[0]);
        String unitMeasure = sizeAndUnitMeasure[1];
        return Size.of(size, unitMeasure);
    }

    private int parseSeeders(String seeders) {
        return parseInteger(seeders);
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

    @Data
    private class TorrentAdditionalData {
        private final Resolution resolution;
        private final Codec codec;
        private final ReleaseType releaseType;
    }
}
