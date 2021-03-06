package org.neoevolution.core.operator.selection;

import org.neoevolution.core.operator.mutation.Mutation;
import org.neoevolution.core.operator.reproduction.Parents;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.Species;
import org.neoevolution.util.MapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractSelection<R extends Reproduction, M extends Mutation> implements Selection {

    protected int populationSize;

    protected int maxSpeciesSize;

    protected R reproduction;

    protected M mutation;


    @Override
    public List<Genotype> select(Population population)
    {
        Long generation = population.getGeneration();
        Double totalFitness = population.getFitness();
        Set<Species> species = population.getSpecies();
        reset(population);

        int totalSize = 0;
        List<Genotype> bestGenotypes = new ArrayList<>(species.size());
        List<Future<Species>> futureSpecies = new ArrayList<>(species.size());
        List<Genotype> offsprings = Collections.synchronizedList(new ArrayList<Genotype>(populationSize));

        for (Species specie : species) {
            futureSpecies.add(select(specie, generation, totalFitness, offsprings));
        }

        for (Future<Species> specie : futureSpecies)
        {
            try {
                Species s = specie.get();

                if (s != null) {
                    population.addSpecies(s);
                    bestGenotypes.add(s.getBestGenotype());
                    totalSize += s.getGenotypes().size();
                }
            }
            catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        totalSize += offsprings.size();
        createMissingOffsprings(generation, totalSize, bestGenotypes, offsprings);
        return offsprings;
    }

    protected void reset(Population population) {
        int size = maxSpeciesSize * 2;
        population.setSpecies(MapUtils.<Species>createHashSet(size));
        population.setBestSpecies(null);
        population.setBestGenotype(null);
    }

    protected abstract Future<Species> select(Species specie, Long generation, Double totalFitness, List<Genotype> offsprings);


    protected void reproduce(Long generation, int births, List<Genotype> genotypes, List<Genotype> offsprings)
    {
        for (int i = 0; i < births; i++) {
            Parents parents = selectParents(genotypes);
            Genotype offspring = reproduction.reproduce(parents, generation);
            mutation.mutate(offspring);
            offsprings.add(offspring);
        }
    }

    protected void createMissingOffsprings(long generation, int totalSize, List<Genotype> genotypes, List<Genotype> offsprings) {
        int births = populationSize - totalSize;
        reproduce(generation, births, genotypes, offsprings);
    }

    protected abstract Parents selectParents(List<Genotype> genotypes);


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

    public R getReproduction() {
        return reproduction;
    }
    public void setReproduction(R reproduction) {
        this.reproduction = reproduction;
    }

    public M getMutation() {
        return mutation;
    }
    public void setMutation(M mutation) {
        this.mutation = mutation;
    }

}
