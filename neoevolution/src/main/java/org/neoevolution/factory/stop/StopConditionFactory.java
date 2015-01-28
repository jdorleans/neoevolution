package org.neoevolution.factory.stop;

import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface StopConditionFactory<T extends StopCondition, C>
        extends ConfigurableFactory<T, C> {

}
