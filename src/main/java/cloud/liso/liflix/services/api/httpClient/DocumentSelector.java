package cloud.liso.liflix.services.api.httpClient;

import java.util.List;

public interface DocumentSelector {
    List<DOMElement> apply(DOMDocument document);
}
