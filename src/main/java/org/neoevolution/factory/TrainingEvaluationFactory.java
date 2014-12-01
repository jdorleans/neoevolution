package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public abstract class TrainingEvaluationFactory<T extends TrainingEvaluation, C extends GAConfiguration>
        extends AbstractConfigurableFactory<T, C>
        implements EvaluationFactory<T, C> {

    protected ErrorFunctionFactory<ErrorFunction> errorFunctionFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        errorFunctionFactory = ClassUtils.create(configuration.getErrorFactory());
    }


    @Override
    public T create() {
        T evaluation = creation();
        evaluation.setErrorFunction(errorFunctionFactory.create());
        return evaluation;
    }

    protected abstract T creation();

}
