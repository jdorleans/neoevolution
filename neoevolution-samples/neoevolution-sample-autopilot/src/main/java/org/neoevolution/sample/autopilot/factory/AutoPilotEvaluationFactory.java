package org.neoevolution.sample.autopilot.factory;

import org.neoevolution.factory.operator.evaluation.NETrainingEvaluationFactory;
import org.neoevolution.sample.autopilot.core.AutoPilotEvaluation;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class AutoPilotEvaluationFactory extends NETrainingEvaluationFactory<AutoPilotEvaluation, AutoPilotConfiguration> {

    @Override
    protected AutoPilotEvaluation creation() {
        return new AutoPilotEvaluation();
    }

}
