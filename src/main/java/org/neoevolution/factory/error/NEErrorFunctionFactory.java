package org.neoevolution.factory.error;

import org.neoevolution.mvc.model.configuration.ErrorConfiguration;
import org.neoevolution.core.error.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 25 2014
 */
public class NEErrorFunctionFactory<C extends ErrorConfiguration<ErrorFunctionType>>
        extends AbstractErrorFunctionFactory<ErrorFunctionType, C> {

    @Override
    public ErrorFunction create()
    {
        ErrorFunctionType type = configuration.getErrorFunctionType();

        if (ErrorFunctionType.isDE(type)) {
            return new DEFunction();
        }
        else if (ErrorFunctionType.isRMSE(type)) {
            return new RMSEFunction();
        }
        return new MSEFunction();
    }

}
