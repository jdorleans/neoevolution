package org.neoevolution.core.error;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
@Component
@Scope("prototype")
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