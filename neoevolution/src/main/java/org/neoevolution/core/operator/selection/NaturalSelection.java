package org.neoevolution.core.operator.selection;

import org.neoevolution.core.operator.mutation.ComposedMutation;
import org.neoevolution.core.operator.reproduction.Crossover;
import org.neoevolution.core.operator.reproduction.Parents;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Species;
import org.neoevolution.util.FitnessUtils;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;

import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 18/10/14.
 */
public class NaturalSelection extends AbstractSelection<Crossover, ComposedMutation> {

    private double survivalRate;

    private double elitismRate;


    @Override
    protected Species selection(Species specie, Long generation, Double totalFitness, Set<Genotype> offsprings)
    {
        Species species = null;
        int newSize = calculateSize(specie, totalFitness);
        int actualSize = specie.getGenotypes().size();
        int births = calculateBirths(actualSize, newSize);
        int survivals = newSize - births;

        List<Genotype> bestFirst = FitnessUtils.sortByFitness(specie.getGenotypes(), true);
        reproduce(generation, births, bestFirst, offsprings);

        if (survivals > 0) {
            kill(survivals, actualSize, bestFirst, specie);
            species = specie;
        }
        return species;
    }

    private void kill(int survivals, int actualSize, List<Genotype> bestFirst, Species species)
    {
        if (survivals < actualSize)
        {
            species.setBestGenotype(null);
            species.setGenotypes(MapUtils.<Genotype>createHashSet(survivals));

            for (int i = 0; i < survivals; i++) {
                species.addGenotype(bestFirst.get(i));
            }
        }
    }

    protected Parents selectParents(List<Genotype> genotypes)
    {
        int size = calculateParents(genotypes.size());
        int pos1 = Randomizer.randomInt(size);
        int pos2 = Randomizer.randomInt(size);
        Genotype g1 = genotypes.get(pos1);
        Genotype g2 = genotypes.get(pos2);
        return new Parents(g1, g2);
    }

    private int calculateSize(Species specie, double totalFitness) {
        return (int) ((specie.getFitness() / totalFitness) * populationSize);
    }

    private int calculateBirths(int actualSize, int newSize)
    {
        if (actualSize < newSize) {
            return newSize - actualSize;
        } else {
            return newSize - calculateSurvivals(newSize);
        }
    }

    private int calculateSurvivals(int size) {
        return (int) Math.round(size * survivalRate);
    }

    private int calculateParents(int size) {
        return Math.max(1, (int) Math.round(size * elitismRate));
    }


    public double getSurvivalRate() {
        return survivalRate;
    }
    public void setSurvivalRate(double survivalRate) {
        this.survivalRate = survivalRate;
    }

    public double getElitismRate() {
        return elitismRate;
    }
    public void setElitismRate(double elitismRate) {
        this.elitismRate = elitismRate;
    }

}