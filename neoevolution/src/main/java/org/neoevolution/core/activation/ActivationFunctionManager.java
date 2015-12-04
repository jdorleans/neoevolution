package org.neoevolution.core.activation;

import org.neoevolution.mvc.converter.ActivationTypeToActivationConverter;
import org.neoevolution.mvc.model.NeuronType;
import org.neoevolution.mvc.model.configuration.Configurable;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@org.springframework.beans.factory.annotation.Configurable
public class ActivationFunctionManager implements Configurable<NNConfiguration> {

    private Map<NeuronType, ActivationFunction> functions;

    @Autowired
    private ActivationTypeToActivationConverter converter;


    @Override
    public void configure(NNConfiguration configuration) {
        functions = MapUtils.createHashMap(4);
        functions.put(NeuronType.BIAS, configuration.getActivationBias());
        functions.put(NeuronType.INPUT, configuration.getActivationInput());
        functions.put(NeuronType.HIDDEN, configuration.getActivationHidden());
        functions.put(NeuronType.OUTPUT, configuration.getActivationOutput());
    }


    public ActivationFunction get(NeuronType type) {
        return functions.get(type);
    }

    public ActivationFunction getRandom() {
        NeuronType[] types = NeuronType.values();
        NeuronType type = types[Randomizer.randomInt(types.length)];
        return functions.get(type);
    }

}
