package org.neoevolution.core.operator.evaluation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.Species;

import java.util.Set;

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

        genotypes.parallelStream().forEach(genotype -> {
            if (!genotype.isEvaluated()) {
                evaluate(genotype);
            }
            adjustFitness(genotype, size);
        });

        for (Genotype genotype : genotypes) {
            species.updateBestGenotype(genotype);
            fitness += genotype.getAdjustedFitness();
        }
        species.setFitness(fitness);
        return fitness;
    }

    protected abstract void evaluate(Genotype genotype);

    protected void adjustFitness(Genotype genotype, int size) {
        genotype.setAdjustedFitness(genotype.getFitness() / size);
    }

}
