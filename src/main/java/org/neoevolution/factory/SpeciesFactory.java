package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Species;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 10 2014
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
        return create(size, 1l);
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
