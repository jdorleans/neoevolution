package org.neoevolution.core.operator.selection;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Genotype;
import org.neoevolution.core.Population;
import org.neoevolution.core.Species;
import org.neoevolution.util.GenotypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
        double totalFitness = population.getFitness();
        int populationSize = configuration.getPopulationSize();
        Iterator<Species> species = population.getSpecies().iterator();

        while(species.hasNext())
        {
            Species specie = species.next();
            calculateMaxSize(specie, totalFitness, populationSize);
            select(specie);

            if (specie.getGenotypes().isEmpty()) {
                species.remove();
            }
        }
    }

    private void calculateMaxSize(Species specie, double totalFitness, int populationSize) {
        int maxSize = (int) ((specie.getFitness() / totalFitness) * populationSize);
        specie.setMaxSize(maxSize);
    }

    private void select(Species species)
    {
        Set<Genotype> genotypes = species.getGenotypes();
        int size = genotypes.size();
        int survivals = (int) (species.getMaxSize() * configuration.getSurvivalRate());

        if (size > survivals)
        {
            int deaths = size - survivals;
            List<Genotype> sorted = GenotypeUtils.sortByFitness(genotypes, false);

            for (int i = 0; i < deaths; i++) {
                genotypes.remove(sorted.get(i));
            }
        }
    }

}
