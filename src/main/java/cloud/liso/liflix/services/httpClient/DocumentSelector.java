package cloud.liso.liflix.services.httpClient;

import java.util.List;

public interface DocumentSelector {
    List<DOMElement> apply(DOMDocument document);
}
