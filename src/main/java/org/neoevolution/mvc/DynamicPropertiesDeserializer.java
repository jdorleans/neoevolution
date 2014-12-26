package org.neoevolution.mvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;
import org.springframework.data.neo4j.fieldaccess.DynamicPropertiesContainer;

import java.io.IOException;
import java.util.Map;

public class DynamicPropertiesDeserializer extends JsonDeserializer<DynamicProperties> {

    @Override
    public DynamicProperties deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        Map map = new ObjectMapper().readValue(jp, Map.class);
        return new DynamicPropertiesContainer(map);
    }

}
