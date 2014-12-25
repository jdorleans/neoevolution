package org.neoevolution.factory.operator.evaluation;

import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface EvaluationFactory<T extends Evaluation, C>
        extends ConfigurableFactory<T, C> {

}
