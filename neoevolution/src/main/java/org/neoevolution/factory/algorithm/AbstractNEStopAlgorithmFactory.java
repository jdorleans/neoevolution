package org.neoevolution.factory.algorithm;

import org.neoevolution.core.algorithm.AbstractNEStopAlgorithm;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.stop.ComposedStop;
import org.neoevolution.factory.stop.NEStopConditionFactory;
import org.neoevolution.mvc.model.configuration.AbstractNEStopConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNEStopAlgorithmFactory<T extends AbstractNEStopAlgorithm<Eva>,
        Eva extends Evaluation, C extends AbstractNEStopConfiguration>
        extends AbstractNEAlgorithmFactory<T, Eva, ComposedStop, C> {

    protected AbstractNEStopAlgorithmFactory() {
        super();
        stopConditionFactory = new NEStopConditionFactory<>();
    }


}
