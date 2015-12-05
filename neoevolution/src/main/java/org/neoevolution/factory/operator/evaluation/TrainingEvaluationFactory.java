package org.neoevolution.factory.operator.evaluation;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class TrainingEvaluationFactory
        <T extends TrainingEvaluation, ET extends ErrorFunctionType, C extends ErrorConfiguration<ET>>
        extends AbstractConfigurableFactory<T, C>
        implements EvaluationFactory<T, C> {

    protected FitnessCalculator fitnessCalculator;


    protected TrainingEvaluationFactory(FitnessCalculator fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }


    @Override
    public T create() {
        T evaluation = creation();
        evaluation.setMaxFitness(configuration.getMaxFitness());
        evaluation.setErrorType(configuration.getErrorFunctionType());
        evaluation.setFitnessCalculator(fitnessCalculator);
        return evaluation;
    }

    protected abstract T creation();

}
