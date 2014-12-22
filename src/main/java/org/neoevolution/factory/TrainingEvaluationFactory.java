package org.neoevolution.factory;

import org.neoevolution.core.configuration.ErrorConfiguration;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public abstract class TrainingEvaluationFactory
        <T extends TrainingEvaluation, ET extends ErrorFunctionType, C extends ErrorConfiguration<ET>>
        extends AbstractConfigurableFactory<T, C>
        implements EvaluationFactory<T, C> {

    protected TrainingErrorFunctionFactory<ET, C> errorFunctionFactory;


    protected TrainingEvaluationFactory() {
        errorFunctionFactory = new TrainingErrorFunctionFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        errorFunctionFactory.configure(configuration);
    }

    @Override
    public T create() {
        T evaluation = creation();
        evaluation.setErrorFunction(errorFunctionFactory.create());
        return evaluation;
    }

    protected abstract T creation();

}
