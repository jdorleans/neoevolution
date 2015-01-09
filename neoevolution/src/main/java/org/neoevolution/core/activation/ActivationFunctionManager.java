package org.neoevolution.core.activation;

import org.neoevolution.mvc.converter.ActivationTypeToActivationConverter;
import org.neoevolution.mvc.model.NeuronType;
import org.neoevolution.mvc.model.configuration.Configurable;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@org.springframework.beans.factory.annotation.Configurable
public class ActivationFunctionManager implements Configurable<NNConfiguration> {

    private Map<NeuronType, ActivationFunction> functions;

    @Autowired
    private ActivationTypeToActivationConverter converter;


    @Override
    public void configure(NNConfiguration configuration) {
        functions = MapUtils.createHashMap(4);
        functions.put(NeuronType.BIAS, converter.convert(configuration.getActivationBias()));
        functions.put(NeuronType.INPUT, converter.convert(configuration.getActivationInput()));
        functions.put(NeuronType.HIDDEN, converter.convert(configuration.getActivationHidden()));
        functions.put(NeuronType.OUTPUT, converter.convert(configuration.getActivationOutput()));
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
