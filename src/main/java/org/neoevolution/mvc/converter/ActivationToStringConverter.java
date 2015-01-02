package org.neoevolution.mvc.converter;

import org.neoevolution.core.activation.ActivationFunction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@Component
public class ActivationToStringConverter implements Converter<ActivationFunction, String> {

    @Override
    public String convert(ActivationFunction source) {
        return source.getType().name();
    }

}
