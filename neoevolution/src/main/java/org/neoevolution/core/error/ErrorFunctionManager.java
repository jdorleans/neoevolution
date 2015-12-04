package org.neoevolution.core.error;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class ErrorFunctionManager {

    @Resource
    private Map<String, ErrorFunction> errorFunctions;


    public ErrorFunction get(ErrorFunctionType type)
    {
        try {
            return errorFunctions.get(type.getName()).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
