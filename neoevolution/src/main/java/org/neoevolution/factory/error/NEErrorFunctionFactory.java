package org.neoevolution.factory.error;

import org.neoevolution.core.error.ErrorFunctionManager;
import org.neoevolution.core.error.NEErrorFunctionType;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class NEErrorFunctionFactory<C extends ErrorConfiguration<NEErrorFunctionType>>
        extends AbstractErrorFunctionFactory<NEErrorFunctionType, C> {

    public NEErrorFunctionFactory(ErrorFunctionManager errorFunctionManager) {
        super(errorFunctionManager);
    }

}
