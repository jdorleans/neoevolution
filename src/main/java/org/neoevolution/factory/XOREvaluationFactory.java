package org.neoevolution.factory;

import org.neoevolution.core.configuration.XORConfiguration;
import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.operator.evaluation.XOREvaluation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class XOREvaluationFactory extends TrainingEvaluationFactory
        <XOREvaluation, ErrorFunctionType, XORConfiguration> {

    @Override
    protected XOREvaluation creation() {
        return new XOREvaluation();
    }

}
