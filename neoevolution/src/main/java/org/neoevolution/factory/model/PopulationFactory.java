package org.neoevolution.factory.model;

import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
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
        speciation.speciate(population, genotypeFactory.createList(configuration.getPopulationSize()));
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
