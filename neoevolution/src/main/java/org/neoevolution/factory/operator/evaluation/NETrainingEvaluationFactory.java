package org.neoevolution.factory.operator.evaluation;

import org.neoevolution.core.error.NEErrorFunctionType;
import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.mvc.model.configuration.ErrorConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class NETrainingEvaluationFactory
        <T extends TrainingEvaluation, C extends ErrorConfiguration<NEErrorFunctionType>>
        extends TrainingEvaluationFactory<T, NEErrorFunctionType, C> {


}
