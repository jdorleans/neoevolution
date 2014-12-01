package org.neoevolution.core.operator.mutation;

import org.neoevolution.factory.NeuronFactory;
import org.neoevolution.factory.SynapseFactory;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.model.Synapse;
import org.neoevolution.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddNeuronMutation extends AbstractMutation {

    public static final int MAX_HIDDEN = 10;

    private int maxHidden;

    private NeuronFactory neuronFactory;

    private SynapseFactory synapseFactory;


    public AddNeuronMutation() {
        this(MAX_HIDDEN);
    }

    public AddNeuronMutation(int maxHidden) {
        super();
        this.maxHidden = maxHidden;
    }

    public AddNeuronMutation(int rate, int maxHidden) {
        super(rate);
        this.maxHidden = maxHidden;
    }


    @Override
    public void mutate(Genotype genotype) {
        if (genotype.getHiddenSize() < maxHidden) {
            super.mutate(genotype);
        }
    }

    @Override
    protected void mutation(Genotype genotype)
    {
        Synapse synapse = selectSynapse(genotype.getSynapses());
        synapse.setEnabled(false);
        Double weight = synapse.getWeight();

        Neuron start = synapse.getStart();
        Neuron end = synapse.getEnd();
        Neuron neuron = createNeuron(genotype, start, end);
        createSynapse(start, neuron, 1d, genotype);
        createSynapse(neuron, end, weight, genotype);
    }

    private Synapse selectSynapse(Set<Synapse> synapses)
    {
        Synapse synapse;
        int size = synapses.size();
        List<Synapse> synaps = new ArrayList<>(synapses);

        do {
            synapse = synaps.get(Randomizer.randomInt(size));
        } while (!synapse.isEnabled());

        return synapse;
    }

    private Neuron createNeuron(Genotype genotype, Neuron start, Neuron end) {
        Neuron neuron = neuronFactory.createHidden(start, end);
        genotype.addNeuron(neuron);
        return neuron;
    }

    private void createSynapse(Neuron start, Neuron end, Double weight, Genotype genotype) {
        Synapse synapse = synapseFactory.create(start, end, weight);
        genotype.addSynapse(synapse);
    }


    public int getMaxHidden() {
        return maxHidden;
    }
    public void setMaxHidden(int maxHidden) {
        this.maxHidden = maxHidden;
    }

    public NeuronFactory getNeuronFactory() {
        return neuronFactory;
    }
    public void setNeuronFactory(NeuronFactory neuronFactory) {
        this.neuronFactory = neuronFactory;
    }

    public SynapseFactory getSynapseFactory() {
        return synapseFactory;
    }
    public void setSynapseFactory(SynapseFactory synapseFactory) {
        this.synapseFactory = synapseFactory;
    }

}
