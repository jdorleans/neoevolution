package org.neoevolution.factory.operator.evaluation;

import org.neoevolution.mvc.model.configuration.ErrorConfiguration;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.factory.error.NEErrorFunctionFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 25 2014
 */
public abstract class NETrainingEvaluationFactory
        <T extends TrainingEvaluation, C extends ErrorConfiguration<ErrorFunctionType>>
        extends TrainingEvaluationFactory<T, ErrorFunctionType, C> {

    protected NETrainingEvaluationFactory() {
        errorFunctionFactory = new NEErrorFunctionFactory<>();
    }

}
