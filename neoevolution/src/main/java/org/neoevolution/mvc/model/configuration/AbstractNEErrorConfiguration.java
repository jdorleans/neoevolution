package org.neoevolution.mvc.model.configuration;

import org.neoevolution.core.error.ErrorFunctionType;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNEErrorConfiguration<T extends ErrorFunctionType>
        extends NEConfiguration implements ErrorConfiguration<T> {

    private static final long serialVersionUID = 6413498924831492508L;

    protected Double maxFitness;

    protected T errorFunctionType;


    public AbstractNEErrorConfiguration() {
        super();
        maxFitness = 1d;
    }


    @Override
    public Double getMaxFitness() {
        return maxFitness;
    }
    public void setMaxFitness(Double maxFitness) {
        this.maxFitness = maxFitness;
    }

    @Override
    public T getErrorFunctionType() {
        return errorFunctionType;
    }
    public void setErrorFunctionType(T errorFunctionType) {
        this.errorFunctionType = errorFunctionType;
    }

}
