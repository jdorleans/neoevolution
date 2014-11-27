package org.neoevolution.core.innovation;

import org.neoevolution.core.model.Neuron;
import org.springframework.stereotype.Component;

@Component
public class NeuronInnovationManager extends InnovationManager<Neuron> {

    public static final String SEPARATOR = ":";


    public NeuronInnovationManager() {
        this(SEPARATOR);
    }

    public NeuronInnovationManager(String code) {
        this(code, CAPACITY);
    }

    public NeuronInnovationManager(String code, int capacity) {
        super(code, capacity);
    }


    private String key(Neuron neuron) {
        return (code + neuron.getInnovation());
    }

    public synchronized Long innovate(Neuron neuron)
    {
        Long innovation = neuron.getInnovation();

        if (innovation == null) {
            innovation = next();
            neuron.setInnovation(innovation);
            innovations.put(key(neuron), innovation);
        }
        return innovation;
    }

}
