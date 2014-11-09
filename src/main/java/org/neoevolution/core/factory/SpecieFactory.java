package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Species;
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

    @Autowired
    private GAConfiguration configuration;


    public Species create(int size)
    {
        Species species = new Species(configuration.getGeneration(), size);

        for (int i = 0; i < size; i++) {
            species.addGenotype(genotypeFactory.create());
        }
        return species;
    }

}
