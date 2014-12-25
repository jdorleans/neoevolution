package org.neoevolution.xor;

import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public class XORAlgorithmFactory extends AbstractNEStopAlgorithmFactory
        <XORAlgorithm, XOREvaluation, XORConfiguration> {

    public XORAlgorithmFactory() {
        super();
        evaluationFactory = new XOREvaluationFactory();
    }

    @Override
    protected XORAlgorithm creation() {
        return new XORAlgorithm();
    }

}
