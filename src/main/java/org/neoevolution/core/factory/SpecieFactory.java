package org.neoevolution.core.factory;

import org.neoevolution.core.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 22/10/14.
 */
@Component
public class SpecieFactory {

    @Autowired
    private GenotypeFactory genotypeFactory;


    public Species create(int size, int generation)
    {
        Species species = new Species(generation, size);

        for (int i = 0; i < size; i++) {
            species.addGenotype(genotypeFactory.create(generation));
        }
        return species;
    }

}
