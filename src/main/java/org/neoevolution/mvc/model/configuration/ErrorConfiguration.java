package org.neoevolution.mvc.model.configuration;

import org.neoevolution.core.error.ErrorFunctionType;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 21 2014
 */
public interface ErrorConfiguration<T extends ErrorFunctionType> {

    T getErrorFunctionType();

}
