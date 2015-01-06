package org.neoevolution.mvc.model.configuration;

import org.neoevolution.core.error.ErrorFunctionType;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 25 2014
 */
public abstract class AbstractNEErrorStopConfiguration<T extends ErrorFunctionType>
        extends AbstractNEStopConfiguration
        implements ErrorConfiguration<T> {

    private static final long serialVersionUID = -2723719776823502835L;

    protected T errorFunctionType;


    public T getErrorFunctionType() {
        return errorFunctionType;
    }
    public void setErrorFunctionType(T errorFunctionType) {
        this.errorFunctionType = errorFunctionType;
    }

}
