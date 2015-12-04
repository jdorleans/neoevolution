package org.neoevolution.core.error;

import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.dataset.EntityDataSet;
import org.neoevolution.mvc.dataset.FitnessDataSet;
import org.neoevolution.mvc.dataset.SampleData;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class FitnessCalculator {

    protected GenotypeActivation activation;

    protected ErrorFunctionManager errorManager;


    @Autowired
    public FitnessCalculator(GenotypeActivation activation, ErrorFunctionManager errorManager) {
        this.activation = activation;
        this.errorManager = errorManager;
    }


    public EntityDataSet calculateEntity(Genotype genotype, FitnessDataSet dataSet) {
        return new EntityDataSet<>(genotype.getId(), calculate(genotype, dataSet));
    }

    public List<Double> calculate(Genotype genotype, FitnessDataSet dataSet)
    {
        List<Double> outputs = new ArrayList<>(dataSet.size());
        Double maxFitness = dataSet.getMaxFitness();
        ErrorFunction errorFunction = errorManager.get(dataSet.getType());

        for (SampleData sampleData : dataSet) {
            outputs.add(calculate(genotype, sampleData, maxFitness, errorFunction));
        }
        return outputs;
    }


    public double calculate(Genotype genotype, SampleData sampleData, Double maxFitness, ErrorFunction errorFunction)
    {
        errorFunction.reset();
        Set<Long> stimulated = activation.stimuliInputs(genotype, sampleData.getInputs());

        int idx = 0;
        for (Neuron neuron : genotype.getOutputs()) {
            double value = activation.activate(neuron, stimulated);
            errorFunction.add(sampleData.getOutput(idx), value);
            idx++;
        }
        return calculate(maxFitness, errorFunction);
    }

    public double calculate(Genotype genotype, SampleData sampleData, Double maxFitness, ErrorFunctionType type) {
        return calculate(genotype, sampleData, maxFitness, errorManager.get(type));
    }

    public double calculate(Double maxFitness, ErrorFunction errorFunction) {
        return (maxFitness - errorFunction.calculate());
    }

}
