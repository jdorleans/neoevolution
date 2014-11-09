package org.neoevolution.core.activation;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.NeuronType;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActivationFunctionManager {

    @Autowired
    private GAConfiguration configuration;

    @Autowired
    private List<ActivationFunction> functions;

    private Map<NeuronType, ActivationFunction> mapFunctions;


    @PostConstruct
    private void init()
    {
        mapFunctions = new HashMap<>(functions.size());
        mapFunctions.put(NeuronType.BIAS, find(configuration.getActivationBias()));
        mapFunctions.put(NeuronType.INPUT, find(configuration.getActivationInput()));
        mapFunctions.put(NeuronType.HIDDEN, find(configuration.getActivationHidden()));
        mapFunctions.put(NeuronType.OUTPUT, find(configuration.getActivationOutput()));
    }


    public ActivationFunction get(NeuronType type)
    {
        ActivationFunction function = mapFunctions.get(type);

        if (function == null) {
            function = functions.get(Randomizer.randomInt(functions.size()));
        }
        return function;
    }

    private ActivationFunction find(ActivationFunctionType type)
    {
        for (ActivationFunction function : functions) {
            if (function.getType().equals(type)) {
                return function;
            }
        }
        return null;
    }

}
