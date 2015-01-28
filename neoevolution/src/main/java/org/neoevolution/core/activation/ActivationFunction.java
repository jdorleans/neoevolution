package org.neoevolution.core.activation;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface ActivationFunction {

    ActivationFunctionType getType();

    double calculate(double input);

    @JsonIgnore
    double getMinimum();

    @JsonIgnore
    double getMaximum();

}
