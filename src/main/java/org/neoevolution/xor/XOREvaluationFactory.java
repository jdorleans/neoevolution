package org.neoevolution.xor;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.factory.TrainingEvaluationFactory;

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
