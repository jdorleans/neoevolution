package org.neoevolution.core.operator.selection;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Genotype;
import org.neoevolution.core.Population;
import org.neoevolution.core.Species;
import org.neoevolution.util.GenotypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 18/10/14.
 */
@Component
public class NaturalSelection implements Selection {

    @Autowired
    private GAConfiguration configuration;

    @Override
    public void select(Population population)
    {
        Set<Species> species = population.getSpecies();
        population.setSpecies(new LinkedHashSet<Species>(species.size()));

        for (Species specie : species) {
            if (select(specie)) {
                population.addSpecie(specie);
            }
            // FIXME - MUST UPDATE BEST SPECIES AND GENOTYPE FOR POPULATION
        }
    }

    // FIXME - REVIEW SURVIVAL VS REPRODUCTION
    private boolean select(Species species)
    {
        Set<Genotype> genotypes = species.getGenotypes();
        int size = genotypes.size();
        int survivalSize = (int) (size * configuration.getSurvivalRate());
        boolean isAlive = survivalSize > 0;

        if (isAlive)
        {
            int deaths = size - survivalSize;
            List<Genotype> sorted = GenotypeUtils.sortByFitness(genotypes, false);

            for (int i = 0; i < deaths; i++) {
                genotypes.remove(sorted.get(i));
            }
        }
        return isAlive;
    }

}
