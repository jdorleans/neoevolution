package org.neoevolution.factory.operator.evaluation;

import org.neoevolution.mvc.model.configuration.ErrorConfiguration;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.error.ErrorFunctionFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public abstract class TrainingEvaluationFactory
        <T extends TrainingEvaluation, ET extends ErrorFunctionType, C extends ErrorConfiguration<ET>>
        extends AbstractConfigurableFactory<T, C>
        implements EvaluationFactory<T, C> {

    protected ErrorFunctionFactory<C> errorFunctionFactory;


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
