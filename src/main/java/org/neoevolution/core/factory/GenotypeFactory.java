package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Genotype;
import org.neoevolution.core.Neuron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 22/10/14.
 */
@Component
public class GenotypeFactory {

    @Autowired
    private NeuronFactory neuronFactory;

    @Autowired
    private SynapseFactory synapseFactory;

    @Autowired
    private GAConfiguration configuration;


    public Genotype create()
    {
        int inputs = configuration.getInputSize();
        int outputs = configuration.getOutputSize();
        Genotype genotype = new Genotype(configuration.getGeneration(), inputs, outputs);
        init(genotype);
        return genotype;
    }

    // TODO - IT SHOULD NOT BE FULLY CONNECTED!
    // TODO - IT MUST BE POSSIBLE TO CONFIGURE THE INITIAL MINIMAL NETWORK
    private void init(Genotype genotype)
    {
        List<Neuron> inputs = neuronFactory.createInputs();
        List<Neuron> outputs = neuronFactory.createOutputs();

        for (Neuron input : inputs)
        {
            genotype.addInput(input);

            for (Neuron output : outputs) {
                genotype.addOutput(output);
                genotype.addSynapse(synapseFactory.create(input, output));
            }
        }
    }

}
