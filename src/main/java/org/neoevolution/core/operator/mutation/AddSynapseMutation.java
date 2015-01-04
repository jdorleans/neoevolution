package org.neoevolution.core.operator.mutation;

import org.neoevolution.factory.model.SynapseFactory;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.NeuronType;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddSynapseMutation extends AbstractMutation {

    private SynapseFactory synapseFactory;


    public AddSynapseMutation() {
        super();
    }

    public AddSynapseMutation(double rate, SynapseFactory synapseFactory) {
        super(rate);
        this.synapseFactory = synapseFactory;
    }


    @Override
    protected void mutation(Genotype genotype)
    {
        Set<Neuron> neurons = genotype.getNeurons();
        int size = neurons.size();
        int maxOutgoing = size - genotype.getInputsSize() - 1;
        Neuron start = selectStart(neurons, maxOutgoing);

        if (start != null)
        {
            int maxIncoming = size - genotype.getOutputsSize() - 1;
            Neuron end = selectEnd(start, neurons, maxIncoming);

            if (end != null) {
                create(start, end, genotype);
            }
        }
    }

    private Neuron selectStart(Set<Neuron> neurons, int maxOutgoing)
    {
        Neuron neuron = null;
        List<Neuron> starts = findStarts(neurons, maxOutgoing);

        if (!starts.isEmpty()) {
            neuron = starts.get(Randomizer.randomInt(starts.size()));
        }
        return neuron;
    }

    private List<Neuron> findStarts(Set<Neuron> neurons, int maxOutgoing)
    {
        List<Neuron> starts = new ArrayList<>(neurons.size());

        for (Neuron neuron : neurons) {
            if (isStart(neuron, maxOutgoing)) {
                starts.add(neuron);
            }
        }
        return starts;
    }

    private boolean isStart(Neuron neuron, int maxOutgoing)
    {
        boolean isStart = false;
        NeuronType type = neuron.getType();

        if (!NeuronType.isOutput(type))
        {
            if (!NeuronType.isHidden(type)) {
                maxOutgoing++;
            }
            isStart = neuron.getOutputs().size() < maxOutgoing;
        }
        return isStart;
    }


    private Neuron selectEnd(Neuron start, Set<Neuron> neurons, int maxIncoming)
    {
        Neuron neuron = null;
        List<Neuron> ends = findEnds(start, neurons, maxIncoming);

        if (!ends.isEmpty()) {
            neuron = ends.get(Randomizer.randomInt(ends.size()));
        }
        return neuron;
    }

    private List<Neuron> findEnds(Neuron start, Set<Neuron> neurons, int maxIncoming)
    {
        List<Neuron> ends = new ArrayList<>(neurons.size());

        for (Neuron neuron : neurons) {
            if (isEnd(start, neuron, maxIncoming)) {
                ends.add(neuron);
            }
        }
        return ends;
    }

    // TODO - Can a neuron be connected to another neuron from the same layer?
    private boolean isEnd(Neuron start, Neuron neuron, int maxIncoming) {
        NeuronType type = neuron.getType();
        boolean isEnd = (!start.equals(neuron) && !NeuronType.isInputOrBias(type));
        return (isEnd && (neuron.getInputs().size() < maxIncoming) && !hasEnd(start, neuron));
    }

    private boolean hasEnd(Neuron start, Neuron neuron)
    {
        boolean hasEnd = false;

        for (Synapse synapse : start.getOutputs()) {
            if (synapse.getEnd().equals(neuron)) {
                hasEnd = true;
                break;
            }
        }
        return hasEnd;
    }

    private Synapse create(Neuron start, Neuron end, Genotype genotype) {
        Synapse synapse = synapseFactory.create(start, end);
        genotype.addSynapse(synapse);
        return synapse;
    }

    public SynapseFactory getSynapseFactory() {
        return synapseFactory;
    }
    public void setSynapseFactory(SynapseFactory synapseFactory) {
        this.synapseFactory = synapseFactory;
    }

}
