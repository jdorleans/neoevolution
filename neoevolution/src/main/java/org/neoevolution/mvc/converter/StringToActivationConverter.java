package org.neoevolution.mvc.converter;

import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.core.activation.ActivationFunctionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class StringToActivationConverter implements Converter<String, ActivationFunction> {

    @Autowired
    private ActivationTypeToActivationConverter converter;

    @Override
    public ActivationFunction convert(String source) {
        return converter.convert(ActivationFunctionType.valueOf(source));
    }

}
