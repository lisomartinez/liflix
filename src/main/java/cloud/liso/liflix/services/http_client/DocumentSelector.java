package cloud.liso.liflix.services.http_client;

import java.util.List;

public interface DocumentSelector {
    List<DOMElement> apply(DOMDocument document);
}
