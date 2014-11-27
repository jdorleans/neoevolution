package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.operator.mutation.AddSynapseMutation;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

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
    private AddSynapseMutation addSynapseMutation;

    @Autowired
    private GAConfiguration configuration;


    public Genotype createEmpty(int generation) {
        Set<Neuron> inputs = neuronFactory.createInputs();
        Set<Neuron> outputs = neuronFactory.createOutputs();
        return new Genotype(generation, inputs, outputs);
    }

    public Genotype create(int generation)
    {
        Genotype genotype = createEmpty(generation);

        if (configuration.isFullyConnected()) {
            connect(genotype);
        } else {
            addSynapseMutation.mutate(genotype, true);
        }
        return genotype;
    }

    private void connect(Genotype genotype)
    {
        for (Neuron input : genotype.getInputs()) {
            for (Neuron output : genotype.getOutputs()) {
                genotype.addSynapse(synapseFactory.create(input, output));
            }
        }
    }

    public Set<Genotype> createList(int size, int generation)
    {
        Set<Genotype> genotypes = MapUtils.createHashSet(size);

        for (int i = 0; i < size; i++) {
            genotypes.add(create(generation));
        }
        return genotypes;
    }

}
