package org.neoevolution.core.error;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class DEFunction extends AbstractErrorFunction<NEErrorFunctionType> {

    @Override
    public NEErrorFunctionType getType() {
        return NEErrorFunctionType.DE;
    }

    @Override
    public void add(double ideal, double actual) {
        error += Math.abs(actual - ideal);
        size++;
    }

    @Override
    protected double compute() {
        return error /= size;
    }

}