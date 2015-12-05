package org.neoevolution.factory.stop;

import org.neoevolution.core.stop.ComposedStop;
import org.neoevolution.core.stop.FitnessStop;
import org.neoevolution.core.stop.GenerationStop;
import org.neoevolution.mvc.model.configuration.StopConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class NEStopConditionFactory<C extends StopConfiguration>
        extends AbstractStopConditionFactory<ComposedStop, C> {

    public NEStopConditionFactory() {
        super(new ComposedStop());
    }

    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        stopCondition.add(new FitnessStop(configuration.getBestFitness()));
        stopCondition.add(new GenerationStop(configuration.getMaxGeneration()));
        stopCondition.setAssertAll(configuration.isAssertAll());
    }

}
