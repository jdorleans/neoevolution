package org.neoevolution.xor;

import org.neoevolution.factory.operator.evaluation.NETrainingEvaluationFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class XOREvaluationFactory extends NETrainingEvaluationFactory<XOREvaluation, XORConfiguration> {

    @Override
    protected XOREvaluation creation() {
        return new XOREvaluation();
    }

}
