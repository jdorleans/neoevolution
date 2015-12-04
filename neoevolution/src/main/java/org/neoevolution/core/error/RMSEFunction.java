package org.neoevolution.core.error;

import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class RMSEFunction extends MSEFunction {

    @Override
    public NEErrorFunctionType getType() {
        return NEErrorFunctionType.RMSE;
    }

    @Override
    protected double compute() {
        return Math.sqrt(super.compute());
    }

}