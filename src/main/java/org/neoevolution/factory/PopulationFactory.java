package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.operator.speciation.Speciation;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public class PopulationFactory<C extends GAConfiguration> extends AbstractConfigurableFactory<Population, C> {

    private Speciation speciation;

    private GenotypeFactory<C> genotypeFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        genotypeFactory = new GenotypeFactory<>();
        genotypeFactory.configure(configuration);
        SpeciationFactory<C> speciationFactory = new SpeciationFactory<>(); // FIXME - GENERIC
        speciationFactory.configure(configuration);
        speciation = speciationFactory.create();
    }


    @Override
    public Population create() {
        Population population = new Population(configuration.getMaxSpeciesSize());
        Set<Genotype> offsprings = genotypeFactory.createList(configuration.getPopulationSize());
        speciation.speciate(population, offsprings);
        return population;
    }

}
