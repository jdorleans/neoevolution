package org.neoevolution.sample.robot.factory;

import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;
import org.neoevolution.sample.robot.core.RobotAlgorithm;
import org.neoevolution.sample.robot.core.RobotEvaluation;
import org.neoevolution.sample.robot.mvc.model.RobotConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class RobotAlgorithmFactory extends AbstractNEStopAlgorithmFactory
        <RobotAlgorithm, RobotEvaluation, RobotConfiguration> {

    public RobotAlgorithmFactory() {
        super();
        evaluationFactory = new RobotEvaluationFactory();
    }

    @Override
    protected RobotAlgorithm creation() {
        return new RobotAlgorithm();
    }

}
