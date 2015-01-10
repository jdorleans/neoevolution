package org.neoevolution.sample.autopilot.factory;

import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;
import org.neoevolution.sample.autopilot.core.AutoPilotAlgorithm;
import org.neoevolution.sample.autopilot.core.AutoPilotEvaluation;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class AutoPilotAlgorithmFactory extends AbstractNEStopAlgorithmFactory
        <AutoPilotAlgorithm, AutoPilotEvaluation, AutoPilotConfiguration> {

    public AutoPilotAlgorithmFactory() {
        super();
        evaluationFactory = new AutoPilotEvaluationFactory();
    }

    @Override
    protected AutoPilotAlgorithm creation() {
        return new AutoPilotAlgorithm();
    }

}
