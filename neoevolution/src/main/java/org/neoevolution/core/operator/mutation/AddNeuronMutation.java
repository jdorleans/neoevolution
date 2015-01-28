package org.neoevolution.core.operator.mutation;

import org.neoevolution.factory.model.NeuronFactory;
import org.neoevolution.factory.model.SynapseFactory;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.util.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
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
        Synapse synapse = selectSynapse(genotype);

        if (synapse != null)
        {
            synapse.setEnabled(false);
            Double weight = synapse.getWeight();
            Neuron start = synapse.getStart();
            Neuron end = synapse.getEnd();
            Neuron neuron = createNeuron(genotype, start, end);
            createSynapse(start, neuron, 1d, genotype);
            createSynapse(neuron, end, weight, genotype);
        }
    }

    private Synapse selectSynapse(Genotype genotype)
    {
        Synapse synapse;
        List<Synapse> synapses = new ArrayList<>(genotype.getSynapses());
        int size = synapses.size();
        int trails = Math.min(size, genotype.getNeuronsSize());

        do {
            trails--;
            synapse = synapses.get(Randomizer.randomInt(size));
        } while (!synapse.isEnabled() && trails > 0);

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
