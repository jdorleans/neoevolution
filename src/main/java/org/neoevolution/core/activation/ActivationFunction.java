package org.neoevolution.core.activation;

public interface ActivationFunction {

    ActivationFunctionType getType();

    double calculate(double input);

    double getMinimum();

    double getMaximum();

}
