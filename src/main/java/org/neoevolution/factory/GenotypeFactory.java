package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.operator.mutation.AddSynapseMutation;
import org.neoevolution.util.MapUtils;

import java.util.Set;

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

    private Long lastGeneration;

    private Long lastInnnovation;

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
        lastGeneration = configuration.getGeneration();
        initAddSynapseMutation(configuration);
    }

    private void initAddSynapseMutation(C configuration) {
        addSynapseMutationFactory.configure(configuration);
        addSynapseMutation = addSynapseMutationFactory.create();
        addSynapseMutation.setRate(1d);
    }


    public Genotype createEmpty(Long generation)
    {
        updateInnovation(generation);
        Set<Neuron> inputs = neuronFactory.createInputs();
        Set<Neuron> outputs = neuronFactory.createOutputs();
        return new Genotype(lastInnnovation, lastGeneration, inputs, outputs);
    }

    private void updateInnovation(Long generation)
    {
        if (lastGeneration < generation) {
            lastGeneration = generation;
            lastInnnovation = 1l;
        } else {
            lastInnnovation++;
        }
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
