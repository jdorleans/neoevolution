package org.neoevolution.factory;

import org.neoevolution.core.configuration.ErrorConfiguration;
import org.neoevolution.core.error.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 21 2014
 */
public class TrainingErrorFunctionFactory<T extends ErrorFunctionType, C extends ErrorConfiguration<T>>
        extends AbstractConfigurableFactory<ErrorFunction, C>
        implements ErrorFunctionFactory<C> {

    @Override
    public ErrorFunction create()
    {
        T type = configuration.getErrorFunctionType();

        if (ErrorFunctionType.isDE(type)) {
            return new DEFunction();
        }
        else if (ErrorFunctionType.isRMSE(type)) {
            return new RMSEFunction();
        }
        return new MSEFunction();
    }

}
