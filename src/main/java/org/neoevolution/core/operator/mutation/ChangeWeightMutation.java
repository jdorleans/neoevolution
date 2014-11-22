package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.Synapse;
import org.neoevolution.util.Randomizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ChangeWeightMutation extends AbstractMutation {

    private double range;

    private boolean reset;

    @Override
    protected void initRate() {
        rate = configuration.getChangeWeightRate();
        range = configuration.getWeightRange();
        reset = configuration.isChangeWeightReset();
    }

    @Override
    protected void mutation(Genotype genotype)
    {
        List<Synapse> synapses = new ArrayList<>(genotype.getSynapses());
        Collections.shuffle(synapses);
        int size = Randomizer.randomIntInclusive(synapses.size());

        for (int i = 0; i < size; i++) {
            Synapse synapse = synapses.get(i);
            synapse.setWeight(calculateWeight(synapse.getWeight()));
        }
    }

    private double calculateWeight(double weight)
    {
        double value = Randomizer.randomInclusive(-range, range);

        if (!reset)
        {
            value += weight;

            if (value > range) {
                value = range;
            } else if (value < -range) {
                value = -range;
            }
        }
        return value;
    }

}
