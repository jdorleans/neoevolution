package org.neoevolution.factory.error;

import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.error.ErrorFunctionManager;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractErrorFunctionFactory
        <T extends ErrorFunctionType, C extends ErrorConfiguration<T>>
        extends AbstractConfigurableFactory<ErrorFunction, C>
        implements ErrorFunctionFactory<C> {

    private ErrorFunctionManager errorFunctionManager;


    protected AbstractErrorFunctionFactory(ErrorFunctionManager errorFunctionManager) {
        this.errorFunctionManager = errorFunctionManager;
    }


    @Override
    public ErrorFunction create() {
        return errorFunctionManager.get(configuration.getErrorFunctionType());
    }


    public ErrorFunctionManager getErrorFunctionManager() {
        return errorFunctionManager;
    }
    public void setErrorFunctionManager(ErrorFunctionManager errorFunctionManager) {
        this.errorFunctionManager = errorFunctionManager;
    }

}
