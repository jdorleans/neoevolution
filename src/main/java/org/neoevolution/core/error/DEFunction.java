package org.neoevolution.core.error;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
@Component
@Scope("prototype")
public class DEFunction extends AbstractErrorFunction {

    @Override
    public ErrorFunctionType getType() {
        return ErrorFunctionType.DE;
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