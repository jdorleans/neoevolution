package org.neoevolution.core.operator.activation;

import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.Future;

@Component
public class NEEvaluation {

    protected Double maxFitness;

    @Autowired
    protected GenotypeActivation activation;

    protected ErrorFunction errorFunction;

    @Async
    public Future<EntitySampleDataSet> evaluate(Genotype genotype, SampleDataSet dataSet)
    {
        EntitySampleDataSet outputs = new EntitySampleDataSet(genotype.getId());

        for (SampleData sampleData : dataSet) {
//            FIXME
//            outputs.add(evaluate(genotype, sampleData));
        }
        return new AsyncResult<>(outputs);
    }

    public double evaluate(Genotype genotype, SampleData sampleData)
    {
        errorFunction.reset();
        Set<Long> stimulated = activation.stimuliInputs(genotype, sampleData.getInputs());

        int idx = 0;
        for (Neuron neuron : genotype.getOutputs()) {
            double value = activation.activate(neuron, stimulated);
            errorFunction.add(sampleData.getOutput(idx), value);
            idx++;
        }
        return calculateError();
    }

    protected double calculateError() {
        return (maxFitness - errorFunction.calculate());
    }

}
