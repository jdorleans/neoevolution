package org.neoevolution.core.error;

import org.neoevolution.core.GAConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
public class ErrorFunctionManager {

    private ErrorFunctionType type;

    private GAConfiguration configuration;


    private void init() {
//        type = configuration.getErrorFactory();
    }


    public ErrorFunction get()
    {
        if (ErrorFunctionType.isDE(type)) {
            return new DEFunction();
        }
        else if (ErrorFunctionType.isMSE(type)) {
            return new MSEFunction();
        }
        else if (ErrorFunctionType.isRMSE(type)) {
            return new RMSEFunction();
        }
        else {
            return new MSEFunction();
        }
    }

}
