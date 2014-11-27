package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 22/10/14.
 */
@Component
public class PopulationFactory {

    @Autowired
    private SpecieFactory specieFactory;

    @Autowired
    private GAConfiguration configuration;


    public Population create()
    {
        int size = configuration.getPopulationSize();
        int maxSpecies = configuration.getMaxSpeciesSize();
        Population population = new Population(maxSpecies);
        Species species = specieFactory.create(size, population.getGeneration());
        population.addSpecie(species);
        population.setBestGenotype(species.getBestGenotype());
        return population;
    }

}
