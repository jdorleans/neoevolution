package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.evaluation.XOREvaluation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class XOREvaluationFactory extends TrainingEvaluationFactory<XOREvaluation, GAConfiguration> {

    @Override
    protected XOREvaluation creation() {
        return new XOREvaluation();
    }

}
