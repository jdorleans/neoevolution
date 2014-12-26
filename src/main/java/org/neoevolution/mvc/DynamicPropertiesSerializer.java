package org.neoevolution.mvc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;

import java.io.IOException;

public class DynamicPropertiesSerializer extends JsonSerializer<DynamicProperties> {

    @Override
    public void serialize(DynamicProperties value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        new ObjectMapper().writeValue(jgen, value.asMap());
    }

}
