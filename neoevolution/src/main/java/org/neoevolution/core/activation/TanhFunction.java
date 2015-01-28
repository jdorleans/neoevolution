package org.neoevolution.core.activation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class TanhFunction extends AbstractActivationFunction implements ActivationFunction {

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
