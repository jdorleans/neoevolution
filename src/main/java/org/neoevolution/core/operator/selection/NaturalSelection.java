package org.neoevolution.core.operator.selection;

import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.model.Species;
import org.neoevolution.core.operator.mutation.Mutation;
import org.neoevolution.core.operator.reproduction.Parents;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.util.GenotypeUtils;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;

import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 18/10/14.
 */
public class NaturalSelection implements Selection {

    private int populationSize;

    private int maxSpeciesSize;

    private double survivalRate;

    private double elitismRate;

    private Reproduction reproduction;

    private Mutation mutation;


    @Override
    public Set<Genotype> select(Population population)
    {
        int generation = population.nextGeneration();
        double totalFitness = population.getFitness();
        Set<Genotype> offsprings = MapUtils.createHashSet(populationSize);
        Set<Species> species = population.getSpecies();
        removeSpecies(population);

        for (Species specie : species)
        {
            int newSize = calculateSize(specie, totalFitness);
            int actualSize = specie.getGenotypes().size();
            int births = calculateBirths(actualSize, newSize);
            int survivals = newSize - births;

            List<Genotype> bestFirst = GenotypeUtils.sortByFitness(specie.getGenotypes(), true);
            reproduce(generation, births, bestFirst, offsprings);

            if (survivals > 0) {
                kill(survivals, actualSize, bestFirst, specie);
                population.addSpecie(specie);
            }
        }
        return offsprings;
    }

    private void removeSpecies(Population population) {
        int size = maxSpeciesSize * 2;
        population.setSpecies(MapUtils.<Species>createLinkedHashSet(size));
        population.setBestSpecies(null);
        population.setBestGenotype(null);
    }

    private void reproduce(int generation, int births, List<Genotype> bestFirst, Set<Genotype> offsprings)
    {
        for (int i = 0; i < births; i++) {
            Parents parents = selectParents(bestFirst);
            Genotype offspring = reproduction.reproduce(parents, generation);
            mutation.mutate(offspring);
            offsprings.add(offspring);
        }
    }

    private void kill(int survivals, int actualSize, List<Genotype> bestFirst, Species species)
    {
        if (survivals < actualSize)
        {
            species.setBestGenotype(null);
            species.setGenotypes(MapUtils.<Genotype>createLinkedHashSet(survivals));

            for (int i = 0; i < survivals; i++) {
                species.addGenotype(bestFirst.get(i));
            }
        }
    }

    private Parents selectParents(List<Genotype> genotypes)
    {
        int pos = 0;
        int size = calculateParents(genotypes.size());
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

    public int getPopulationSize() {
        return populationSize;
    }
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getMaxSpeciesSize() {
        return maxSpeciesSize;
    }
    public void setMaxSpeciesSize(int maxSpeciesSize) {
        this.maxSpeciesSize = maxSpeciesSize;
    }

    public double getElitismRate() {
        return elitismRate;
    }
    public void setElitismRate(double elitismRate) {
        this.elitismRate = elitismRate;
    }

    public Reproduction getReproduction() {
        return reproduction;
    }
    public void setReproduction(Reproduction reproduction) {
        this.reproduction = reproduction;
    }

    public Mutation getMutation() {
        return mutation;
    }
    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

}
