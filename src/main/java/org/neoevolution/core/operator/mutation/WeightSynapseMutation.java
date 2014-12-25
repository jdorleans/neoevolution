package org.neoevolution.core.operator.mutation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.util.Randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightSynapseMutation extends AbstractMutation {

    public static final double RANGE = 1000;

    private double range;

    private boolean reset;


    public WeightSynapseMutation() {
        this(RANGE, false);
    }

    public WeightSynapseMutation(double range, boolean reset) {
        super();
        this.range = range;
        this.reset = reset;
    }

    public WeightSynapseMutation(double rate, double range, boolean reset) {
        super(rate);
        this.range = range;
        this.reset = reset;
    }


    @Override
    protected void mutation(Genotype genotype)
    {
        List<Synapse> synapses = new ArrayList<>(genotype.getSynapses());
        Collections.shuffle(synapses);
        int size = Randomizer.randomIntInclusive(synapses.size());

        for (int i = 0; i < size; i++) {
            mutate(synapses.get(i));
        }
    }

    public void mutate(Synapse synapse)
    {
        double weight = Randomizer.randomInclusive(-range, range);

        if (!reset)
        {
            weight += synapse.getWeight();

            if (weight > range) {
                weight = range;
            } else if (weight < -range) {
                weight = -range;
            }
        }
        synapse.setWeight(weight);
    }

    public boolean isReset() {
        return reset;
    }
    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public double getRange() {
        return range;
    }
    public void setRange(double range) {
        this.range = range;
    }

}
