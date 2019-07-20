package cloud.liso.liflix.services.torrent.parsing;

import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.services.http_client.DOMElement;
import org.springframework.stereotype.Component;

@Component
public class StringBasedElementsParser implements ElementsParser {

    @Override
    public Torrent.TorrentBuilder parse(DOMElement element, Selectors selectors, Torrent.TorrentBuilder builder) {
        selectors.getSelectorsByType().entrySet()
                .stream().filter(e -> !e.getKey().equals(SelectorType.SIZE))
                .forEach(e -> setBuilderElement(e.getKey(), e.getValue().apply(element), builder));
        return builder;
    }

    private void setBuilderElement(SelectorType key, String parsedElement, Torrent.TorrentBuilder builder) {
        switch (key) {
            case TITLE:
                builder.title(parsedElement);
                break;
            case MAGNET:
                builder.magnetLink(parsedElement);
                break;
            case LEECHERS:
                builder.leechers(parseInteger(parsedElement));
                break;
            case SEEDERS:
                builder.seeders(parseInteger(parsedElement));
                break;
            default:
                throw new RuntimeException("Not yet implemented");
        }
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

}
