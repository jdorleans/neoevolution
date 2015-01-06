package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
public class RMSEFunction extends MSEFunction {

    @Override
    public ErrorFunctionType getType() {
        return ErrorFunctionType.RMSE;
    }

    @Override
    protected double compute() {
        return Math.sqrt(super.compute());
    }

}