package org.neoevolution.core.activation;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ActivationFunction {

    ActivationFunctionType getType();

    double calculate(double input);

    @JsonIgnore
    double getMinimum();

    @JsonIgnore
    double getMaximum();

}
