package org.neoevolution.core.operator.selection;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Genotype;
import org.neoevolution.core.Population;
import org.neoevolution.core.Species;
import org.neoevolution.core.operator.mutation.MutationManager;
import org.neoevolution.core.operator.reproduction.Parents;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.util.GenotypeUtils;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;
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
    private Reproduction reproduction;

    @Autowired
    private MutationManager mutation;

    @Autowired
    private GAConfiguration configuration;

    // FIXME
//    @Override
    public void select2(Population population)
    {
        Set<Species> species = population.getSpecies();
        population.setSpecies(new LinkedHashSet<Species>(species.size()));
        population.setBestSpecies(null);
        population.setBestGenotype(null);

        for (Species specie : species) {
            if (select(specie)) {
                population.addSpecie(specie);
            }
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


    @Override
    public Set<Genotype> select(Population population)
    {
        int generation = population.nextGeneration();
        double totalFitness = population.getFitness();
        int populationSize = configuration.getPopulationSize();
        Set<Species> species = population.getSpecies();
        Set<Genotype> offsprings = MapUtils.createHashSet(populationSize);
        population.setSpecies(MapUtils.<Species>createLinkedHashSet(species.size()));
        population.setBestSpecies(null);
        population.setBestGenotype(null);

        for (Species specie : species)
        {
            int size = calculateSpeciesSize(specie, totalFitness, populationSize);

            if (size > 0)
            {
                List<Genotype> genotypes = GenotypeUtils.sortByFitness(specie.getGenotypes(), true);

                for (int i = 0; i < size; i++) {
                    offsprings.add(reproduce(generation, genotypes));
                }
                specie.setGenotypes(MapUtils.<Genotype>createLinkedHashSet(size + 1));
                specie.addGenotype(specie.getBestGenotype());
                population.addSpecie(specie);
            }
        }
        return offsprings;
    }

    private int calculateSpeciesSize(Species specie, double totalFitness, int populationSize) {
        return (int) ((specie.getFitness() / totalFitness) * populationSize);
    }

    private Genotype reproduce(int generation, List<Genotype> genotypes) {
        Parents parents = selectParents(genotypes);
        Genotype offspring = reproduction.reproduce(parents, generation);
        mutation.mutate(offspring);
        return offspring;
    }

    private Parents selectParents(List<Genotype> genotypes)
    {
        int pos = 0;
        int size = calculateParentSize(genotypes.size());
        int pos1 = Randomizer.randomInt(size);
        int pos2 = Randomizer.randomInt(size);
        Genotype g1 = null;
        Genotype g2 = null;

        for (Genotype genotype : genotypes)
        {
            if (pos == pos1) {
                g1 = genotype;
            }
            if (pos == pos2) {
                g2 = genotype;
            }
            if (g1 != null && g2 != null) {
                break;
            }
            pos++;
        }
        return new Parents(g1, g2);
    }

    private int calculateParentSize(int size) {
        return Math.max(1, (int) (size * configuration.getElitismRate()));
    }

}
