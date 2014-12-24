package org.neoevolution.xor;

import org.neoevolution.core.stop.ComposedStop;
import org.neoevolution.core.stop.FitnessStop;
import org.neoevolution.core.stop.GenerationStop;
import org.neoevolution.factory.AbstractStopConditionFactory;
import org.neoevolution.factory.StopConditionFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 18 2014
 */
public class XORStopConditionFactory
        extends AbstractStopConditionFactory<ComposedStop, XORConfiguration>
        implements StopConditionFactory<ComposedStop, XORConfiguration> {

    public XORStopConditionFactory() {
        super(new ComposedStop());
    }

    @Override
    public void configure(XORConfiguration configuration) {
        super.configure(configuration);
        stopCondition.add(new FitnessStop(configuration.getFitness()));
        stopCondition.add(new GenerationStop(configuration.getMaxGeneration()));
        stopCondition.setAssertAll(configuration.isAssertAll());
    }

}
