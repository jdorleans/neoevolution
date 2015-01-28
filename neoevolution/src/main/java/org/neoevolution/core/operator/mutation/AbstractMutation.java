package org.neoevolution.core.operator.mutation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.util.Randomizer;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractMutation implements Mutation {

    public static final double RATE = 0.5;

    protected double rate;


    protected AbstractMutation() {
        this(RATE);
    }

    protected AbstractMutation(double rate) {
        this.rate = rate;
    }


    protected abstract void mutation(Genotype genotype);

    @Override
    public void mutate(Genotype genotype) {
        if (Randomizer.randomBoolean(rate)) {
            mutation(genotype);
        }
    }

    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

}
