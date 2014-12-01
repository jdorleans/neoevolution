package org.neoevolution.factory;

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
public class GenotypeFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<Genotype, C>
        implements ConfigurableFactory<Genotype, C> {

    private NeuronFactory<C> neuronFactory;

    private SynapseFactory<C> synapseFactory;

    private AddSynapseMutation addSynapseMutation;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        neuronFactory = new NeuronFactory<>();
        neuronFactory.configure(configuration);
        synapseFactory = new SynapseFactory<>();
        synapseFactory.configure(configuration);
        initAddSynapseMutation(configuration);
    }

    private void initAddSynapseMutation(GAConfiguration configuration) {
        AddSynapseMutationFactory<GAConfiguration> factory = ClassUtils.create(configuration.getAddSynapseMutationFactory());
        factory.configure(configuration);
        addSynapseMutation = factory.create();
        addSynapseMutation.setRate(1d);
    }


    public Genotype createEmpty(int generation) {
        Set<Neuron> inputs = neuronFactory.createInputs();
        Set<Neuron> outputs = neuronFactory.createOutputs();
        return new Genotype(generation, inputs, outputs);
    }

    @Override
    public Genotype create() {
        return create(0);
    }

    public Genotype create(int generation)
    {
        Genotype genotype = createEmpty(generation);

        if (configuration.isFullyConnected()) {
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
