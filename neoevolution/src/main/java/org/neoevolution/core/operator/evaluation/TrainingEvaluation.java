package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.mvc.dataset.SampleData;
import org.neoevolution.mvc.model.Genotype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Configurable
public abstract class TrainingEvaluation extends AbstractEvaluation {

    public static final double MAX_FITNESS = 1d;

    protected Double maxFitness;

    protected List<SampleData> data;

    protected ErrorFunctionType errorType;

    @Autowired
    protected FitnessCalculator fitnessCalculator;

    @Autowired
    protected AsyncTrainingEvaluation asyncEvaluation;


    protected TrainingEvaluation() {
        this(MAX_FITNESS);
    }

    protected TrainingEvaluation(Double maxFitness) {
        this.maxFitness = maxFitness;
        this.data = new ArrayList<>();
    }


    @Override
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

    @Override
    protected Future<Genotype> evaluateAsync(Genotype genotype) {
        return asyncEvaluation.evaluate(genotype, this);
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
