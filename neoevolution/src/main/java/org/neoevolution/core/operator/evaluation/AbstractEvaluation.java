package org.neoevolution.core.operator.evaluation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractEvaluation implements Evaluation {

    @Override
    public void evaluate(Population population)
    {
        double fitness = 0d;

        for (Species species : population.getSpecies()) {
            fitness += evaluate(species);
            population.updateBestSpecies(species);
        }
        population.setFitness(fitness);
    }

    protected double evaluate(Species species)
    {
        double fitness = 0d;
        Set<Genotype> genotypes = species.getGenotypes();
        int size = genotypes.size();
        List<Future<Genotype>> futureGenotypes = new ArrayList<>(size);

        for (Genotype genotype : genotypes)
        {
            if (!genotype.isEvaluated()) {
                futureGenotypes.add(evaluate(genotype));
            } else {
                species.updateBestGenotype(genotype);
                fitness += adjustFitness(genotype, size);
            }
        }

        for (Future<Genotype> futureGenotype : futureGenotypes)
        {
            try {
                Genotype genotype = futureGenotype.get();
                species.updateBestGenotype(genotype);
                fitness += adjustFitness(genotype, size);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        species.setFitness(fitness);
        return fitness;
    }

    protected abstract Future<Genotype> evaluate(Genotype genotype);

    protected double adjustFitness(Genotype genotype, int size) {
        double adjustedFitness = genotype.getFitness() / size;
        genotype.setAdjustedFitness(adjustedFitness);
        return adjustedFitness;
    }

}
