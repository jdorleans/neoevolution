package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.Neuron;
import org.neoevolution.core.Synapse;
import org.neoevolution.core.factory.NeuronFactory;
import org.neoevolution.core.factory.SynapseFactory;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AddNeuronMutation extends AbstractMutation {

    @Autowired
    private NeuronFactory factory;

    @Autowired
    private SynapseFactory synapseFactory;


    @Override
    protected void initRate() {
        rate = configuration.getAddNeuronRate();
    }


    @Override
    public void mutate(Genotype genotype) {
        if (genotype.getHiddensSize() < configuration.getHiddenMaxSize()) {
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
        Neuron neuron = factory.createHidden(start, end);
        genotype.addNeuron(neuron);
        return neuron;
    }

    private void createSynapse(Neuron start, Neuron end, Double weight, Genotype genotype) {
        Synapse synapse = synapseFactory.create(start, end, weight);
        genotype.addSynapse(synapse);
    }

}
