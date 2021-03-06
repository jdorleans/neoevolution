package org.neoevolution.factory.error;

import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface ErrorFunctionFactory<C extends ErrorConfiguration>
        extends ConfigurableFactory<ErrorFunction, C>  {

}
