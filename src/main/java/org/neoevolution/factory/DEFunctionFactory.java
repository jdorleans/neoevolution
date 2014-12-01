package org.neoevolution.factory;

import org.neoevolution.core.error.DEFunction;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class DEFunctionFactory implements ErrorFunctionFactory<DEFunction> {

    @Override
    public DEFunction create() {
        return new DEFunction();
    }

}
