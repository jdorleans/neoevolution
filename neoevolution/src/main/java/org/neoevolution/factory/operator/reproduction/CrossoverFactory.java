package org.neoevolution.factory.operator.reproduction;

import org.neoevolution.core.operator.reproduction.Crossover;
import org.neoevolution.factory.model.GenotypeFactory;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NEConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class CrossoverFactory<C extends NEConfiguration>
        extends AbstractConfigurableFactory<Crossover, C>
        implements ReproductionFactory<Crossover, C> {

    private GenotypeFactory<C> genotypeFactory;


    public CrossoverFactory() {
        genotypeFactory = new GenotypeFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
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
