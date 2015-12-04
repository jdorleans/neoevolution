package org.neoevolution.core.activation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface ActivationFunction<T extends ActivationFunctionType> {

    @JsonUnwrapped
    T getType();

    double calculate(double input);

    @JsonIgnore
    double getMinimum();

    @JsonIgnore
    double getMaximum();

}
