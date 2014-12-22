package org.neoevolution.factory;

import org.neoevolution.core.configuration.NEConfiguration;
import org.neoevolution.core.operator.speciation.NESpeciation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class NESpeciationFactory<C extends NEConfiguration>
        extends AbstractConfigurableFactory<NESpeciation, C>
        implements SpeciationFactory<NESpeciation, C> {

    private SpeciesFactory<C> speciesFactory;


    public NESpeciationFactory() {
        speciesFactory = new SpeciesFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        speciesFactory.configure(configuration);
    }

    @Override
    public NESpeciation create() {
        NESpeciation speciation = new NESpeciation();
        speciation.setPopulationSize(configuration.getPopulationSize());
        speciation.setMaxSpeciesSize(configuration.getMaxSpeciesSize());
        speciation.setThreshold(configuration.getCompatibilityThreshold());
        speciation.setExcessFactor(configuration.getExcessFactor());
        speciation.setWeightFactor(configuration.getWeightFactor());
        speciation.setCompatibilityRate(configuration.getCompatibilityRate());
        speciation.setSpeciesFactory(speciesFactory);
        return speciation;
    }

}
