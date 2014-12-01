package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.Species;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 10 2014
 */
public class SpeciesFactory<C extends GAConfiguration> implements ConfigurableFactory<Species, C> {

    private GenotypeFactory<C> genotypeFactory;


    @Override
    public void configure(C configuration) {
        genotypeFactory = new GenotypeFactory<>();
        genotypeFactory.configure(configuration);
    }

    @Override
    public Species create() {
        return create(0, 0);
    }

    public Species create(int size) {
        return create(size, 0);
    }

    public Species create(int size, int generation)
    {
        Species species = new Species(generation, size);

        for (int i = 0; i < size; i++) {
            species.addGenotype(genotypeFactory.create(generation));
        }
        return species;
    }

}
