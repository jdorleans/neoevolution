package org.neoevolution.core.activation;

import org.neoevolution.util.Randomizer;

public abstract class AbstractActivationFunction implements ActivationFunction {

    protected abstract double calculation(double input);

    @Override
	public double calculate(double input) {
        return Randomizer.round6(calculation(input));
    }

}
