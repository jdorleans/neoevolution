package org.neoevolution.core.activation;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class BinaryFunction extends AbstractActivationFunction {

    @Override
    public NEActivationFunctionType getType() {
        return NEActivationFunctionType.BINARY;
    }

    @Override
	public double calculate(double input) {
        return (input >= 0 ? 1 : 0);
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
