package org.neoevolution.factory.error;

import org.neoevolution.mvc.model.configuration.ErrorConfiguration;
import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ErrorFunctionFactory<C extends ErrorConfiguration>
        extends ConfigurableFactory<ErrorFunction, C>  {

}
