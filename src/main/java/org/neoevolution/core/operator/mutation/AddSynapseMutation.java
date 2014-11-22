package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.Neuron;
import org.neoevolution.core.NeuronType;
import org.neoevolution.core.Synapse;
import org.neoevolution.core.factory.SynapseFactory;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AddSynapseMutation extends AbstractMutation {

    @Autowired
    private SynapseFactory factory;


    @Override
    protected void initRate() {
        rate = configuration.getAddSynapseRate();
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
        boolean isOutput = NeuronType.isOutput(type);

        if (!isOutput)
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
        boolean isEnd = (!start.equals(neuron) && !NeuronType.isBias(type) && !NeuronType.isInput(type));
        return (isEnd && neuron.getInputs().size() < maxIncoming && !hasEnd(start, neuron));
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
        Synapse synapse = factory.create(start, end);
        genotype.getSynapses().add(synapse);
        return synapse;
    }

}