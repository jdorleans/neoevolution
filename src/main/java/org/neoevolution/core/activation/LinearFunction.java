package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

@Component
public final class LinearFunction implements ActivationFunction {

    @Override
    public ActivationFunctionType getType() {
        return ActivationFunctionType.LINEAR;
    }

    @Override
	public double calculate(double input) {
        return input;
    }

    @Override
    public double getMinimum() {
        return Double.MIN_VALUE;
    }

    @Override
    public double getMaximum() {
        return Double.MAX_VALUE;
    }

}
