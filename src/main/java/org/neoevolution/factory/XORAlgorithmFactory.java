package org.neoevolution.factory;

import org.neoevolution.core.algorithm.XORAlgorithm;
import org.neoevolution.core.configuration.XORConfiguration;
import org.neoevolution.core.operator.evaluation.XOREvaluation;
import org.neoevolution.core.stop.FitnessStop;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public class XORAlgorithmFactory
        extends AbstractNEAlgorithmFactory<XORAlgorithm, XOREvaluation, FitnessStop, XORConfiguration> {

    public XORAlgorithmFactory() {
        super();
        evaluationFactory = new XOREvaluationFactory();
        stopConditionFactory = new XORStopConditionFactory();
    }

    @Override
    protected XORAlgorithm creation() {
        return new XORAlgorithm();
    }

}
