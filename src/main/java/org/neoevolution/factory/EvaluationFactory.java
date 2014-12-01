package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.evaluation.Evaluation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface EvaluationFactory<T extends Evaluation, C extends GAConfiguration>
        extends ConfigurableFactory<T, C> {

}
