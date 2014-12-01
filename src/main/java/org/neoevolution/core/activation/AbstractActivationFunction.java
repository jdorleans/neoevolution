package org.neoevolution.core.activation;

import org.neoevolution.util.Randomizer;

public abstract class AbstractActivationFunction implements ActivationFunction {

    protected abstract double calculation(double input);

    @Override
	public double calculate(double input) {
        return Randomizer.round6(calculation(input));
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        } else if (obj instanceof AbstractActivationFunction) {
            return getType().equals(((AbstractActivationFunction) obj).getType());
        }
        return false;
    }

    @Override
    public String toString() {
        return getType().toString();
    }

}
