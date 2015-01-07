package org.neoevolution.sample.xor.factory;

import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;
import org.neoevolution.sample.xor.core.XORAlgorithm;
import org.neoevolution.sample.xor.mvc.model.XORConfiguration;
import org.neoevolution.sample.xor.core.XOREvaluation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
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
