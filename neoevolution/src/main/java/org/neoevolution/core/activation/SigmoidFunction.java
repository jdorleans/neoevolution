package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class SigmoidFunction extends AbstractActivationFunction<NEActivationFunctionType> {

    @Override
    public NEActivationFunctionType getType() {
        return NEActivationFunctionType.SIGMOID;
    }

    @Override
	public double calculate(double input) {
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
