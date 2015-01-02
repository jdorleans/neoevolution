package org.neoevolution.mvc.converter;

import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.core.activation.ActivationFunctionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@Component
public class ActivationTypeToActivationConverter implements Converter<ActivationFunctionType, ActivationFunction> {

    @Resource(name = "activationFunctions")
    private Map<ActivationFunctionType, ActivationFunction> activations;

    @Override
    public ActivationFunction convert(ActivationFunctionType source) {
        return activations.get(source);
    }

}
