package org.neoevolution.mvc.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.core.activation.ActivationFunctionType;
import org.neoevolution.mvc.converter.ActivationTypeToActivationConverter;
import org.neoevolution.util.JsonDeserializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 26 2014
 */
@Component
public class ActivationDeserializer extends JsonDeserializer<ActivationFunction> {

    @Autowired
    private ActivationTypeToActivationConverter converter;

    @Override
    public ActivationFunction deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode function = jp.getCodec().readTree(jp);
        String type = JsonDeserializeUtils.asString(function, "type");
        ActivationFunctionType source = ActivationFunctionType.valueOf(type);
        return converter.convert(source);
    }

}
