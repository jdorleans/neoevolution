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

    @Override
    protected void initRate() {
        rate = configuration.getChangeWeightRate();
    }

    @Override
    protected void mutation(Genotype genotype)
    {
        List<Synapse> synapses = new ArrayList<>(genotype.getSynapses());
        Collections.shuffle(synapses);
        int size = Randomizer.randomIntInclusive(synapses.size());

        for (int i = 0; i < size; i++) {
            synapses.get(i).setWeight(Randomizer.randomInclusive());
        }
    }

}
