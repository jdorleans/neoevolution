package org.neoevolution.core.operator.selection;

import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.model.Species;
import org.neoevolution.core.operator.mutation.Mutation;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.util.MapUtils;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 20 2014
 */
public abstract class AbstractSelection<R extends Reproduction, M extends Mutation> implements Selection {

    protected int populationSize;

    protected int maxSpeciesSize;

    protected R reproduction;

    protected M mutation;


    @Override
    public Set<Genotype> select(Population population)
    {
        Long generation = population.getGeneration();
        Double totalFitness = population.getFitness();
        Set<Genotype> offsprings = MapUtils.createHashSet(populationSize);
        Set<Species> species = population.getSpecies();
        reset(population);

        for (Species specie : species)
        {
            Species s = selection(specie, generation, totalFitness, offsprings);

            if (s != null) {
                population.addSpecies(s);
            }
        }
        return offsprings;
    }

    protected void reset(Population population) {
        int size = maxSpeciesSize * 2;
        population.setSpecies(MapUtils.<Species>createLinkedHashSet(size));
        population.setBestSpecies(null);
        population.setBestGenotype(null);
    }

    protected abstract Species selection(Species specie, Long generation, Double totalFitness, Set<Genotype> offsprings);


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
