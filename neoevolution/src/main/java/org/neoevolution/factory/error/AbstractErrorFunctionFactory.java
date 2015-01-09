package org.neoevolution.factory.error;

import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 21 2014
 */
public abstract class AbstractErrorFunctionFactory
        <T extends ErrorFunctionType, C extends ErrorConfiguration<T>>
        extends AbstractConfigurableFactory<ErrorFunction, C>
        implements ErrorFunctionFactory<C> {

}
