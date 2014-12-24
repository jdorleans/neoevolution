package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.factory.AbstractConfigurableFactory;
import org.neoevolution.factory.StopConditionFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 24 2014
 */
public abstract class AbstractStopConditionFactory<T extends StopCondition, C extends NNConfiguration>
        extends AbstractConfigurableFactory<T, C>
        implements StopConditionFactory<T, C> {

    protected T stopCondition;

    public AbstractStopConditionFactory(T stopCondition) {
        this.stopCondition = stopCondition;
    }

    @Override
    public T create() {
        return stopCondition;
    }

}
