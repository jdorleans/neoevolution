package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class LinearFunction extends AbstractActivationFunction<NEActivationFunctionType> {

    @Override
    public NEActivationFunctionType getType() {
        return NEActivationFunctionType.LINEAR;
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
