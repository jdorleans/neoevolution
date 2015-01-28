package org.neoevolution.core.activation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class LinearFunction extends AbstractActivationFunction implements ActivationFunction {

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
