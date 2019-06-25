package cloud.liso.liflix.services.torrent.parsingUtils;

import cloud.liso.liflix.services.httpClient.DOMElement;
import cloud.liso.liflix.services.httpClient.ElementSelector;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class StringBasedElementsParser implements ElementsParser {

    @Override
    public TorrentElements parse(DOMElement element, Selectors selectors) {
        return new TorrentElements(extractStringBasedElements(element, selectors));
    }

    private Map<SelectorType, TorrentElement<?>> extractStringBasedElements(DOMElement element, Selectors selectors) {
        return selectors.getSelectorsByType().entrySet()
                .stream().filter(e -> !e.getKey().equals(SelectorType.SIZE))
                .collect(toMap(Map.Entry::getKey, e -> createTorrentElement(extractElement(element, e.getValue()))));
    }

    private String extractElement(DOMElement element, ElementSelector value) {
        return value.apply(element);
    }

    private TorrentElement<String> createTorrentElement(String element) {
        return new TorrentElement<>(element);
    }
}
