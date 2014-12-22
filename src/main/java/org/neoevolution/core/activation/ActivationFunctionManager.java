package org.neoevolution.core.activation;

import org.neoevolution.core.configuration.Configurable;
import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.model.NeuronType;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;

import java.util.Map;

public class ActivationFunctionManager implements Configurable<NNConfiguration> {

    private Map<NeuronType, ActivationFunction> functions;

    @Override
    public void configure(NNConfiguration configuration)
    {
        functions = MapUtils.createHashMap(4);
        functions.put(NeuronType.BIAS, create(configuration.getActivationBias()));
        functions.put(NeuronType.INPUT, create(configuration.getActivationInput()));
        functions.put(NeuronType.HIDDEN, create(configuration.getActivationHidden()));
        functions.put(NeuronType.OUTPUT, create(configuration.getActivationOutput()));
    }

    private ActivationFunction create(ActivationFunctionType type)
    {
        if (ActivationFunctionType.isLINEAR(type)) {
            return new LinearFunction();
        }
        else if (ActivationFunctionType.isTANH(type)) {
            return new TanhFunction();
        }
        return new SigmoidFunction();
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
