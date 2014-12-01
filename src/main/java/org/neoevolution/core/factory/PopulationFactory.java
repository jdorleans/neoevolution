package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.operator.speciation.Speciation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public class PopulationFactory {

    private int populationSize;

    private int maxSpeciesSize;

    private Speciation speciation;

    private GenotypeFactory genotypeFactory;


    public PopulationFactory(GAConfiguration configuration) {
        populationSize = configuration.getPopulationSize();
        maxSpeciesSize = configuration.getMaxSpeciesSize();
        genotypeFactory = new GenotypeFactory(configuration);
        speciation = new SpeciationFactory(configuration).create();
    }


    public Population create() {
        Population population = new Population(maxSpeciesSize);
        speciation.speciate(population, genotypeFactory.createList(populationSize));
        return population;
    }

}
