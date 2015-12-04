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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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

        final AtomicInteger totalSize = new AtomicInteger(0);
        List<Species> survivals = Collections.synchronizedList(new ArrayList<>(maxSpeciesSize));
        List<Genotype> offsprings = Collections.synchronizedList(new ArrayList<>(populationSize));
        List<Genotype> bestGenotypes = Collections.synchronizedList(new ArrayList<>(maxSpeciesSize));

        species.parallelStream().forEach(specie -> {
            Species survival = select(specie, generation, totalFitness, offsprings);
            bestGenotypes.add(survival.getBestGenotype());
            totalSize.addAndGet(survival.getGenotypes().size());
            survivals.add(survival);
        });
        survivals.forEach(population::addSpecies);

        totalSize.addAndGet(offsprings.size());
        int births = populationSize - totalSize.get();
        reproduce(generation, births, bestGenotypes, offsprings);
        return offsprings;
    }

    protected void reset(Population population) {
        population.setSpecies(MapUtils.<Species>createHashSet(maxSpeciesSize));
        population.setBestSpecies(null);
        population.setBestGenotype(null);
    }

    protected abstract Species select(Species specie, Long generation, Double totalFitness, List<Genotype> offsprings);


    protected void reproduce(Long generation, int births, List<Genotype> genotypes, List<Genotype> offsprings)
    {
        IntStream.of(births).parallel().forEach(i -> {
            Parents parents = selectParents(genotypes);
            Genotype offspring = reproduction.reproduce(parents, generation);
            mutation.mutate(offspring);
            offsprings.add(offspring);
        });
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
