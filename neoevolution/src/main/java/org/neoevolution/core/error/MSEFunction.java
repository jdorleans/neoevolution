package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class MSEFunction extends AbstractErrorFunction {

    @Override
    public ErrorFunctionType getType() {
        return ErrorFunctionType.MSE;
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