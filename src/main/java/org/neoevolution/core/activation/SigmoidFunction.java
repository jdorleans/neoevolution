package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

@Component
public final class SigmoidFunction extends AbstractActivationFunction implements ActivationFunction {

    @Override
    public ActivationFunctionType getType() {
        return ActivationFunctionType.SIGMOID;
    }

    @Override
	public double calculation(double input) {
        return (1d / (1d + Math.exp(-input)));
    }

    @Override
    public double getMinimum() {
        return 0;
    }

    @Override
    public double getMaximum() {
        return 1;
    }

}
