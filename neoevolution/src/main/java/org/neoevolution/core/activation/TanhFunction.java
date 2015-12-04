package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class TanhFunction extends AbstractActivationFunction<NEActivationFunctionType> {

    @Override
    public NEActivationFunctionType getType() {
        return NEActivationFunctionType.TANH;
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
