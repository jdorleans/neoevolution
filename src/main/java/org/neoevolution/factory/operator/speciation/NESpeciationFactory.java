package org.neoevolution.factory.operator.speciation;

import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.core.operator.speciation.NESpeciation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.model.SpeciesFactory;

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
