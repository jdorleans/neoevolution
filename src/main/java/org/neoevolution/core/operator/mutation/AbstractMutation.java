package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Genotype;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractMutation implements Mutation {

    public static final double RATE = 0.5;

    protected double rate;

    @Autowired
    protected GAConfiguration configuration;


    protected AbstractMutation() {
        this.rate = RATE;
    }


    @PostConstruct
    protected abstract void initRate();

    protected abstract void mutation(Genotype genotype);

    @Override
    public void mutate(Genotype genotype) {
        mutate(genotype, false);
    }

    @Override
    public void mutate(Genotype genotype, boolean ignoreRate) {
        if (ignoreRate || Randomizer.randomBoolean(rate)) {
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
