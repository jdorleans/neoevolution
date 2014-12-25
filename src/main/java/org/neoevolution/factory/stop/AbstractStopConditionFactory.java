package org.neoevolution.factory.stop;

import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 24 2014
 */
public abstract class AbstractStopConditionFactory<T extends StopCondition, C>
        extends AbstractConfigurableFactory<T, C>
        implements StopConditionFactory<T, C> {

    protected T stopCondition;

    protected AbstractStopConditionFactory(T stopCondition) {
        this.stopCondition = stopCondition;
    }

    @Override
    public T create() {
        return stopCondition;
    }

}
