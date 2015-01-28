package org.neoevolution.mvc.model.configuration;

import org.neoevolution.core.error.ErrorFunctionType;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface ErrorConfiguration<T extends ErrorFunctionType> {

    T getErrorFunctionType();

}
