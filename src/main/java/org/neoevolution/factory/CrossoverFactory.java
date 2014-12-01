package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.reproduction.Crossover;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class CrossoverFactory
        extends AbstractConfigurableFactory<Crossover, GAConfiguration>
        implements ReproductionFactory<Crossover, GAConfiguration> {

    private GenotypeFactory<GAConfiguration> genotypeFactory;

    @Override
    public void configure(GAConfiguration configuration) {
        super.configure(configuration);
        genotypeFactory = new GenotypeFactory<>();
        genotypeFactory.configure(configuration);
    }

    @Override
    public Crossover create() {
        Crossover crossover = new Crossover();
        crossover.setEnableSynapseRate(configuration.getEnableSynapseRate());
        crossover.setEnableSynapsePenalty(configuration.getEnableSynapsePenalty());
        crossover.setGenotypeFactory(genotypeFactory);
        return crossover;
    }

}
