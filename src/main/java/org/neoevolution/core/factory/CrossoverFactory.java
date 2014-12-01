package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.reproduction.Crossover;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class CrossoverFactory
        extends AbstractConfigurableFactory<Crossover, GAConfiguration>
        implements ReproductionFactory<Crossover, GAConfiguration> {

    @Override
    public Crossover create() {
        return new Crossover();
    }

}
