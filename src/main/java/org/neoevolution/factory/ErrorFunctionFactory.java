package org.neoevolution.factory;

import org.neoevolution.core.configuration.ErrorConfiguration;
import org.neoevolution.core.error.ErrorFunction;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ErrorFunctionFactory<C extends ErrorConfiguration>
        extends ConfigurableFactory<ErrorFunction, C>  {

}
