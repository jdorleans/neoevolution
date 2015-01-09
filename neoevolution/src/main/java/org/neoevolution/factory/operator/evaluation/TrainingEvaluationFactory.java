package org.neoevolution.factory.operator.evaluation;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public abstract class TrainingEvaluationFactory
        <T extends TrainingEvaluation, ET extends ErrorFunctionType, C extends ErrorConfiguration<ET>>
        extends AbstractConfigurableFactory<T, C>
        implements EvaluationFactory<T, C> {


    @Override
    public T create() {
        T evaluation = creation();
        evaluation.setErrorType(configuration.getErrorFunctionType());
        return evaluation;
    }

    protected abstract T creation();

}
