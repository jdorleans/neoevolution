package org.neoevolution.sample.autopilot.factory;

import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.operator.evaluation.EvaluationFactory;
import org.neoevolution.sample.autopilot.core.AutoPilotEvaluation;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class AutoPilotEvaluationFactory
        extends AbstractConfigurableFactory<AutoPilotEvaluation, AutoPilotConfiguration>
        implements EvaluationFactory<AutoPilotEvaluation, AutoPilotConfiguration> {

    @Override
    public AutoPilotEvaluation create() {
        return new AutoPilotEvaluation();
    }

}
