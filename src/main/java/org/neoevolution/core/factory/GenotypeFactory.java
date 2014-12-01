package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.operator.mutation.AddSynapseMutation;
import org.neoevolution.util.ClassUtils;
import org.neoevolution.util.MapUtils;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 10 2014
 */
public class GenotypeFactory {

    private boolean isFullyConnected;

    private NeuronFactory neuronFactory;

    private SynapseFactory synapseFactory;

    private AddSynapseMutation addSynapseMutation;


    public GenotypeFactory(GAConfiguration configuration) {
        isFullyConnected = configuration.isFullyConnected();
        neuronFactory = new NeuronFactory(configuration);
        synapseFactory = new SynapseFactory(configuration);
        initAddSynapseMutation(configuration, synapseFactory);
    }

    private void initAddSynapseMutation(GAConfiguration configuration, SynapseFactory synapseFactory) {
        addSynapseMutation = ClassUtils.create(configuration.getAddSynapseFunction());
        addSynapseMutation.setRate(1d);
        addSynapseMutation.setSynapseFactory(synapseFactory);
    }


    public Genotype createEmpty(int generation) {
        Set<Neuron> inputs = neuronFactory.createInputs();
        Set<Neuron> outputs = neuronFactory.createOutputs();
        return new Genotype(generation, inputs, outputs);
    }

    public Genotype create(int generation)
    {
        Genotype genotype = createEmpty(generation);

        if (isFullyConnected) {
            connect(genotype);
        } else {
            addSynapseMutation.mutate(genotype);
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

    public Set<Genotype> createList(int size) {
        return createList(size, 0);
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
