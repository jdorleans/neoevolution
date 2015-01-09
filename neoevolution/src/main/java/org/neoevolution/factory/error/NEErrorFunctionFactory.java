package org.neoevolution.factory.error;

import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.error.ErrorFunctionManager;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 25 2014
 */
@Configurable(preConstruction = true)
public class NEErrorFunctionFactory<C extends ErrorConfiguration<ErrorFunctionType>>
        extends AbstractErrorFunctionFactory<ErrorFunctionType, C> {

    @Autowired
    private ErrorFunctionManager errorFunctionManager;

    @Override
    public ErrorFunction create() {
        return errorFunctionManager.get(configuration.getErrorFunctionType());
    }

}
