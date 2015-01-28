package org.neoevolution.core.algorithm;

import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.stop.ComposedStop;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNEStopAlgorithm<Eva extends Evaluation>
        extends AbstractNEAlgorithm<Eva, ComposedStop> {

}
