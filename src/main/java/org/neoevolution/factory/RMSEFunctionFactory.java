package org.neoevolution.factory;

import org.neoevolution.core.error.RMSEFunction;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class RMSEFunctionFactory extends MSEFunctionFactory {

    @Override
    public RMSEFunction create() {
        return new RMSEFunction();
    }

}
