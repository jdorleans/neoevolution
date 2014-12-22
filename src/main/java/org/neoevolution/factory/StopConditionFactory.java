package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.stop.StopCondition;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 18 2014
 */
public interface StopConditionFactory<T extends StopCondition, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
