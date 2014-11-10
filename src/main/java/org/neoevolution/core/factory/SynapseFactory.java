package org.neoevolution.core.factory;

import org.neoevolution.core.Neuron;
import org.neoevolution.core.Synapse;
import org.neoevolution.core.innovation.SynapseInnovationManager;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 22/10/14.
 */
@Component
public class SynapseFactory {

    @Autowired
    private SynapseInnovationManager innovation;


    public Synapse create(Neuron start, Neuron end) {
        return create(start, end, Randomizer.randomInclusive(-1, 1));
    }

    public Synapse create(Neuron start, Neuron end, Double weight)
    {
        Synapse synapse = new Synapse(start, end, weight, true);
        innovation.innovate(synapse, start, end);
        start.addOutput(synapse);
        end.addInput(synapse);
        return synapse;
    }

}
