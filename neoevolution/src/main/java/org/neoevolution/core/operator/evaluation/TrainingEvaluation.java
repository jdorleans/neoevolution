package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.mvc.dataset.SampleData;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configurable
public abstract class TrainingEvaluation implements Evaluation {

    public static final double MAX_FITNESS = 1d;

    protected Double maxFitness;

    protected List<SampleData> data;

    protected ErrorFunctionType errorType;

    @Autowired
    protected FitnessCalculator fitnessCalculator;


    protected TrainingEvaluation() {
        this(MAX_FITNESS);
    }

    protected TrainingEvaluation(Double maxFitness) {
        this.maxFitness = maxFitness;
        this.data = new ArrayList<>();
    }


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

        for (Genotype genotype : genotypes)
        {
            if (!genotype.isEvaluated()) {
                evaluate(genotype);
            }
            species.updateBestGenotype(genotype);
            fitness += adjustFitness(genotype, size);
        }
        species.setFitness(fitness);
        return fitness;
    }

    protected void evaluate(Genotype genotype)
    {
        double fitness = 0d;
        int evaluations = data.size();

        for (SampleData sampleData : data) {
            fitness += fitnessCalculator.calculate(genotype, sampleData, maxFitness, errorType);
        }
        genotype.setFitness(fitness / evaluations);
        genotype.setEvaluated(true);
    }

    protected double adjustFitness(Genotype genotype, int size) {
        double adjustedFitness = genotype.getFitness() / size;
        genotype.setAdjustedFitness(adjustedFitness);
        return adjustedFitness;
    }


    public Double getMaxFitness() {
        return maxFitness;
    }
    public void setMaxFitness(Double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public List<SampleData> getData() {
        return data;
    }
    public void setData(List<SampleData> data) {
        this.data = data;
    }

    public ErrorFunctionType getErrorType() {
        return errorType;
    }
    public void setErrorType(ErrorFunctionType errorType) {
        this.errorType = errorType;
    }

    public FitnessCalculator getFitnessCalculator() {
        return fitnessCalculator;
    }
    public void setFitnessCalculator(FitnessCalculator fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }

}
