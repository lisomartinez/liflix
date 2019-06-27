package cloud.liso.liflix.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class ShowUpdateDeserializer extends StdDeserializer<ShowUpdateDto> {

    public ShowUpdateDeserializer() {
        this(null);
    }

    public ShowUpdateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ShowUpdateDto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        Map<Integer, LocalDateTime> map = new HashMap<>();
        node.fields().forEachRemaining(n -> map.put(Integer.parseInt(n.getKey()), LocalDateTime.ofEpochSecond(n.getValue().asLong(), 0, ZoneOffset.UTC)));
        return new ShowUpdateDto(map);
    }
}
