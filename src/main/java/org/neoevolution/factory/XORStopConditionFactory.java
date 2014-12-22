package org.neoevolution.factory;

import org.neoevolution.core.configuration.XORConfiguration;
import org.neoevolution.core.stop.FitnessStop;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 18 2014
 */
public class XORStopConditionFactory
        extends AbstractConfigurableFactory<FitnessStop, XORConfiguration>
        implements StopConditionFactory<FitnessStop, XORConfiguration> {

    private FitnessStop fitnessStop;

    public XORStopConditionFactory() {
        fitnessStop = new FitnessStop();
    }

    @Override
    public void configure(XORConfiguration configuration) {
        super.configure(configuration);
        fitnessStop.setGoal(configuration.getFitness());
    }

    @Override
    public FitnessStop create() {
        return fitnessStop;
    }

}
