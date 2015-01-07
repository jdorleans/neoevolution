package org.neoevolution.sample.robot.factory;

import org.neoevolution.factory.operator.evaluation.NETrainingEvaluationFactory;
import org.neoevolution.sample.robot.core.RobotEvaluation;
import org.neoevolution.sample.robot.mvc.model.RobotConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class RobotEvaluationFactory extends NETrainingEvaluationFactory<RobotEvaluation, RobotConfiguration> {

    @Override
    protected RobotEvaluation creation() {
        return new RobotEvaluation();
    }

}
