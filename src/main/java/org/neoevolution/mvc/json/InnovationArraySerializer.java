package org.neoevolution.mvc.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 26 2014
 */
@Component
public class InnovationArraySerializer extends JsonSerializer<Iterable<AbstractInnovationEntity>> {

    @Override
    public void serialize(Iterable<AbstractInnovationEntity> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        InnovationSerializer innovationSerializer = new InnovationSerializer();

        jgen.writeStartArray();
        for (AbstractInnovationEntity entity : value) {
            innovationSerializer.serialize(entity, jgen, provider);
        }
        jgen.writeEndArray();
    }

}
