package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
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