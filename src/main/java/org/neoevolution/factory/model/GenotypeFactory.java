package org.neoevolution.factory.model;

import org.neoevolution.core.operator.mutation.AddSynapseMutation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.factory.operator.mutation.AddSynapseMutationFactory;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.util.MapUtils;

import java.util.Set;
import java.util.SortedSet;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 10 2014
 */
public class GenotypeFactory<C extends NNConfiguration>
        extends AbstractConfigurableFactory<Genotype, C>
        implements ConfigurableFactory<Genotype, C> {

    private NeuronFactory<C> neuronFactory;

    private SynapseFactory<C> synapseFactory;

    private AddSynapseMutationFactory<C> addSynapseMutationFactory;

    private AddSynapseMutation addSynapseMutation;


    public GenotypeFactory() {
        this.neuronFactory = new NeuronFactory<>();
        this.synapseFactory = new SynapseFactory<>();
        this.addSynapseMutationFactory = new AddSynapseMutationFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        neuronFactory.configure(configuration);
        synapseFactory.configure(configuration);
        initAddSynapseMutation(configuration);
    }

    private void initAddSynapseMutation(C configuration) {
        addSynapseMutationFactory.configure(configuration);
        addSynapseMutation = addSynapseMutationFactory.create();
        addSynapseMutation.setRate(1d);
    }


    public Genotype createEmpty(Long generation) {
        SortedSet<Neuron> inputs = neuronFactory.createInputs();
        SortedSet<Neuron> outputs = neuronFactory.createOutputs();
        return new Genotype(configuration.nextGenotypeInnovation(), generation, inputs, outputs);
    }

    @Override
    public Genotype create() {
        return create(1l);
    }

    public Genotype create(Long generation)
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

    public Set<Genotype> createList(Integer size) {
        return createList(size, 1l);
    }

    public Set<Genotype> createList(Integer size, Long generation)
    {
        Set<Genotype> genotypes = MapUtils.createHashSet(size);

        for (int i = 0; i < size; i++) {
            genotypes.add(create(generation));
        }
        return genotypes;
    }


    public NeuronFactory<C> getNeuronFactory() {
        return neuronFactory;
    }
    public void setNeuronFactory(NeuronFactory<C> neuronFactory) {
        this.neuronFactory = neuronFactory;
    }

    public SynapseFactory<C> getSynapseFactory() {
        return synapseFactory;
    }
    public void setSynapseFactory(SynapseFactory<C> synapseFactory) {
        this.synapseFactory = synapseFactory;
    }

    public AddSynapseMutationFactory<C> getAddSynapseMutationFactory() {
        return addSynapseMutationFactory;
    }
    public void setAddSynapseMutationFactory(AddSynapseMutationFactory<C> addSynapseMutationFactory) {
        this.addSynapseMutationFactory = addSynapseMutationFactory;
    }

}
