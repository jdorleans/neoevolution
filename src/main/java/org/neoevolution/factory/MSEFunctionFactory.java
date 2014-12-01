package org.neoevolution.factory;

import org.neoevolution.core.error.MSEFunction;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class MSEFunctionFactory implements ErrorFunctionFactory<MSEFunction> {

    @Override
    public MSEFunction create() {
        return new MSEFunction();
    }

}
