package org.neoevolution.sample.soundfilter.factory;

import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;
import org.neoevolution.sample.soundfilter.core.SFAlgorithm;
import org.neoevolution.sample.soundfilter.core.SFEvaluation;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFAlgorithmFactory extends AbstractNEStopAlgorithmFactory
        <SFAlgorithm, SFEvaluation, SFConfiguration> {

    public SFAlgorithmFactory() {
        super();
        evaluationFactory = new SFEvaluationFactory();
    }

    @Override
    protected SFAlgorithm creation() {
        return new SFAlgorithm();
    }

}
