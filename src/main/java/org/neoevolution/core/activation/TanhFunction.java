package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

@Component
public final class TanhFunction implements ActivationFunction {

    @Override
    public ActivationFunctionType getType() {
        return ActivationFunctionType.TANH;
    }

    @Override
	public double calculate(double input) {
        return Math.tanh(input);
    }

    @Override
    public double getMinimum() {
        return -1;
    }

    @Override
    public double getMaximum() {
        return 1;
    }

}
