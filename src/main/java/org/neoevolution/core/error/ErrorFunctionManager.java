package org.neoevolution.core.error;

import org.neoevolution.core.GAConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
@Component
public class ErrorFunctionManager {

    private ErrorFunctionType type;

    @Autowired
    private GAConfiguration configuration;


    @PostConstruct
    private void init() {
        type = configuration.getErrorFunction();
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
