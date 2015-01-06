package org.neoevolution.core.activation;

public class BinaryFunction extends AbstractActivationFunction implements ActivationFunction {

    @Override
    public ActivationFunctionType getType() {
        return ActivationFunctionType.BINARY;
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
