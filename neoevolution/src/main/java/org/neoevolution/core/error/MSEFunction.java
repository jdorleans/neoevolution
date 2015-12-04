package org.neoevolution.core.error;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class MSEFunction extends AbstractErrorFunction<NEErrorFunctionType> {

    @Override
    public NEErrorFunctionType getType() {
        return NEErrorFunctionType.MSE;
    }

    @Override
    public void add(double ideal, double actual) {
        double delta = (actual - ideal);
        error += (delta * delta);
        size++;
    }

    @Override
    protected double compute() {
        return error /= size;
    }

}