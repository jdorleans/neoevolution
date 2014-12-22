package org.neoevolution.core.configuration;

import org.neoevolution.core.error.ErrorFunctionType;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 21 2014
 */
public abstract class ErrorConfiguration<T extends ErrorFunctionType> extends NEConfiguration {

    private static final long serialVersionUID = 6413498924831492508L;

    private T errorFunctionType;


    public T getErrorFunctionType() {
        return errorFunctionType;
    }
    public void setErrorFunctionType(T errorFunctionType) {
        this.errorFunctionType = errorFunctionType;
    }

}
