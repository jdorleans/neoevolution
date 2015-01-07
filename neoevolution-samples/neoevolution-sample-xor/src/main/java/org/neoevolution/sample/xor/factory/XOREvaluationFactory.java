package org.neoevolution.sample.xor.factory;

import org.neoevolution.factory.operator.evaluation.NETrainingEvaluationFactory;
import org.neoevolution.sample.xor.mvc.model.XORConfiguration;
import org.neoevolution.sample.xor.core.XOREvaluation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class XOREvaluationFactory extends NETrainingEvaluationFactory<XOREvaluation, XORConfiguration> {

    @Override
    protected XOREvaluation creation() {
        return new XOREvaluation();
    }

}
