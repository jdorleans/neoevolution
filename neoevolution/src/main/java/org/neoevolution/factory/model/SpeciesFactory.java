package org.neoevolution.factory.model;

import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Species;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SpeciesFactory<C extends NNConfiguration>
        extends AbstractConfigurableFactory<Species, C>
        implements ConfigurableFactory<Species, C> {

    private GenotypeFactory<C> genotypeFactory;


    public SpeciesFactory() {
        genotypeFactory = new GenotypeFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        genotypeFactory.configure(configuration);
    }

    @Override
    public Species create() {
        return create(0);
    }

    public Species create(int size) {
        return create(size, 1L);
    }

    public Species create(int size, Long generation)
    {
        Species species = new Species(configuration.nextSpeciesInnovation(), generation, size);

        for (int i = 0; i < size; i++) {
            species.addGenotype(genotypeFactory.create(generation));
        }
        return species;
    }

    public Species create(Genotype genotype, Long generation)
    {
        int size = Math.max(1, configuration.getPopulationSize() / configuration.getMaxSpeciesSize());
        Species species = new Species(configuration.nextSpeciesInnovation(), generation, size);
        species.addGenotype(genotype);
        return species;
    }


    public GenotypeFactory<C> getGenotypeFactory() {
        return genotypeFactory;
    }
    public void setGenotypeFactory(GenotypeFactory<C> genotypeFactory) {
        this.genotypeFactory = genotypeFactory;
    }

}
