package org.neoevolution.sample.soundfilter.factory;

import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.factory.operator.evaluation.NETrainingEvaluationFactory;
import org.neoevolution.sample.soundfilter.core.SFEvaluation;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFEvaluationFactory extends NETrainingEvaluationFactory<SFEvaluation, SFConfiguration> {

    public SFEvaluationFactory(FitnessCalculator fitnessCalculator) {
        super(fitnessCalculator);
    }

    @Override
    protected SFEvaluation creation() {
        return new SFEvaluation();
    }

}
