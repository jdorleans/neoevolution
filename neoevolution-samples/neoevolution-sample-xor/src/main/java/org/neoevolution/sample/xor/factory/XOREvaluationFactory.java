package org.neoevolution.sample.xor.factory;

import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.factory.operator.evaluation.NETrainingEvaluationFactory;
import org.neoevolution.sample.xor.core.XOREvaluation;
import org.neoevolution.sample.xor.mvc.model.XORConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class XOREvaluationFactory extends NETrainingEvaluationFactory<XOREvaluation, XORConfiguration> {

    public XOREvaluationFactory(FitnessCalculator fitnessCalculator) {
        super(fitnessCalculator);
    }

    @Override
    protected XOREvaluation creation() {
        return new XOREvaluation();
    }

}
