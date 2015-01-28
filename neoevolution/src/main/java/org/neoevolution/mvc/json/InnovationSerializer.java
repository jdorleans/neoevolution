package org.neoevolution.mvc.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class InnovationSerializer extends JsonSerializer<AbstractInnovationEntity> {

    @Override
    public void serialize(AbstractInnovationEntity value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("innovation", value.getInnovation());
        jgen.writeEndObject();
    }

}
