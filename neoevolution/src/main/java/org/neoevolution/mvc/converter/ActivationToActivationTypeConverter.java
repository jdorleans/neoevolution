package org.neoevolution.mvc.converter;

import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.core.activation.ActivationFunctionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@Component
public class ActivationToActivationTypeConverter implements Converter<ActivationFunction, ActivationFunctionType> {

    @Override
    public ActivationFunctionType convert(ActivationFunction source) {
        return source.getType();
    }

}
