package org.neoevolution.core.operator;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractOperation implements Operation {

    public static final double RATE = 0.5;

    protected double rate;

    @Autowired
    protected GAConfiguration configuration;


    protected AbstractOperation() {
        this.rate = RATE;
    }


    @PostConstruct
    protected abstract void initRate();

    @Override
    public boolean operate() {
        return (Randomizer.randomBoolean(rate));
    }


    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

}
