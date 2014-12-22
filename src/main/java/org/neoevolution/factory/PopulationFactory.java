package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.operator.speciation.Speciation;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public class PopulationFactory<C extends NNConfiguration>
        extends AbstractConfigurableFactory<Population, C> {

    private Speciation speciation;

    private GenotypeFactory<C> genotypeFactory;


    public PopulationFactory() {
        this(null);
    }

    public PopulationFactory(Speciation speciation) {
        this.speciation = speciation;
        this.genotypeFactory = new GenotypeFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        genotypeFactory.configure(configuration);
    }


    @Override
    public Population create() {
        Population population = new Population(configuration.nextPopulationInnovation(), configuration.getMaxSpeciesSize());
        Set<Genotype> offsprings = genotypeFactory.createList(configuration.getPopulationSize());
        speciation.speciate(population, offsprings);
        return population;
    }


    public Speciation getSpeciation() {
        return speciation;
    }
    public void setSpeciation(Speciation speciation) {
        this.speciation = speciation;
    }

    public GenotypeFactory<C> getGenotypeFactory() {
        return genotypeFactory;
    }
    public void setGenotypeFactory(GenotypeFactory<C> genotypeFactory) {
        this.genotypeFactory = genotypeFactory;
    }

}
