package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.speciation.Speciation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class SpeciationFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<Speciation, C> {

    @Override
    public Speciation create() {
        Speciation speciation = new Speciation();
        speciation.setPopulationSize(configuration.getPopulationSize());
        speciation.setMaxSpeciesSize(configuration.getMaxSpeciesSize());
        speciation.setThreshold(configuration.getCompatibilityThreshold());
        speciation.setExcessFactor(configuration.getExcessFactor());
        speciation.setWeightFactor(configuration.getWeightFactor());
        speciation.setCompatibilityRate(configuration.getCompatibilityRate());
        return speciation;
    }

}
