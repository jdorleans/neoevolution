package org.neoevolution.xor;

import org.neoevolution.core.stop.ComposedStop;
import org.neoevolution.factory.AbstractNEAlgorithmFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public class XORAlgorithmFactory
        extends AbstractNEAlgorithmFactory<XORAlgorithm, XOREvaluation, ComposedStop, XORConfiguration> {

    public XORAlgorithmFactory() {
        super();
        evaluationFactory = new XOREvaluationFactory();
        stopConditionFactory = new XORStopConditionFactory();
    }

    @Override
    protected XORAlgorithm creation() {
        return new XORAlgorithm();
    }

}
