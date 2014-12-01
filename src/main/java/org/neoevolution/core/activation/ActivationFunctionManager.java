package org.neoevolution.core.activation;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.NeuronType;
import org.neoevolution.util.ClassUtils;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;

import java.util.Map;

public class ActivationFunctionManager {

    private Map<NeuronType, ActivationFunction> functions;


    public ActivationFunctionManager(GAConfiguration configuration) {
        configure(configuration);
    }

    public void configure(GAConfiguration configuration)
    {
        functions = MapUtils.createHashMap(4);
        configure(NeuronType.BIAS, configuration.getActivationBias());
        configure(NeuronType.INPUT, configuration.getActivationInput());
        configure(NeuronType.HIDDEN, configuration.getActivationHidden());
        configure(NeuronType.OUTPUT, configuration.getActivationOutput());
    }

    public void configure(NeuronType type, String name) {
        functions.put(type, ClassUtils.<ActivationFunction>create(name));
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
