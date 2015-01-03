package org.neoevolution.core.operator.activation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.NeuronType;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.util.MapUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@Component
public class GenotypeActivation {

    @Async
    public Future<EntitySampleData> activate(Genotype genotype, SampleData inputs)
    {
        EntitySampleData outputs = new EntitySampleData(genotype.getId());

        for (List<Double> input : inputs) {
            outputs.add(activate(genotype, input));
        }
        return new AsyncResult<>(outputs);
    }

    public List<Double> activate(Genotype genotype, List<Double> inputs)
    {
        Set<Neuron> neurons = genotype.getOutputs();
        List<Double> outputs = new ArrayList<>(neurons.size());
        Set<Long> stimulated = stimuliInputs(genotype, inputs);

        for (Neuron neuron : neurons) {
            outputs.add(activate(neuron, stimulated));
        }
        return outputs;
    }

    public Set<Long> stimuliInputs(Genotype genotype, List<Double> inputs)
    {
        int idx = 0;
        Set<Long> stimulated = MapUtils.createHashSet(genotype.getNeuronsSize());

        for (Neuron neuron : genotype.getInputs())
        {
            neuron.reset();
            NeuronType type = neuron.getType();
            stimulated.add(neuron.getInnovation());

            if (NeuronType.isInput(type)) {
                neuron.impulse(inputs.get(idx));
                idx++;
            } else if (NeuronType.isBias(type)) {
                neuron.impulse(1d);
            }
        }
        return stimulated;
    }

    public double activate(Neuron neuron, Set<Long> stimulated)
    {
        if (!stimulated.contains(neuron.getInnovation()))
        {
            neuron.reset();
            stimulated.add(neuron.getInnovation());

            for (Synapse synapse : neuron.getInputs())
            {
                if (synapse.isEnabled()) {
                    double activation = activate(synapse.getStart(), stimulated);
                    neuron.impulse(activation * synapse.getWeight());
                }
            }
        }
        return neuron.activate();
    }

}
