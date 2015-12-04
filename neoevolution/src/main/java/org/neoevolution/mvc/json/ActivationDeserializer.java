package org.neoevolution.mvc.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.mvc.converter.ActivationTypeToActivationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class ActivationDeserializer extends JsonDeserializer<ActivationFunction> {

    @Autowired
    private ActivationTypeToActivationConverter converter;

    @Override
    public ActivationFunction deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return converter.convert(jp.getText());
    }

}
