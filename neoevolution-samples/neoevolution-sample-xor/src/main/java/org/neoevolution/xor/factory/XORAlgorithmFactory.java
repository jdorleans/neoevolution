package org.neoevolution.xor.factory;

import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;
import org.neoevolution.xor.core.XORAlgorithm;
import org.neoevolution.xor.mvc.model.XORConfiguration;
import org.neoevolution.xor.core.XOREvaluation;

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
