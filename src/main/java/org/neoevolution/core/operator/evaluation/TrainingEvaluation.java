package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.mvc.model.*;
import org.neoevolution.util.MapUtils;

import java.util.List;
import java.util.Set;

public abstract class TrainingEvaluation implements Evaluation {

    public static final double MAX_FITNESS = 1d;

    protected Double maxFitness;

    protected List<List<Double>> inputSet;

    protected List<List<Double>> outputSet;

    protected ErrorFunction errorFunction;


    protected TrainingEvaluation() {
        this(MAX_FITNESS);
    }

    protected TrainingEvaluation(Double maxFitness) {
        this.maxFitness = maxFitness;
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
        int evaluations = inputSet.size();

        for (int idx = 0; idx < evaluations; idx++) {
            fitness += evaluate(genotype, inputSet.get(idx), outputSet.get(idx));
        }
        genotype.setFitness(fitness / evaluations);
        genotype.setEvaluated(true);
    }

    protected double evaluate(Genotype genotype, List<Double> inputs, List<Double> outputs)
    {
        errorFunction.reset();
        Set<Long> stimulated = stimuliInputs(genotype, inputs);

        int idx = 0;
        for (Neuron neuron : genotype.getOutputs()) {
            double activation = activate(neuron, stimulated);
            errorFunction.add(outputs.get(idx), activation);
            idx++;
        }
        return calculateError();
    }

    protected double calculateError() {
        return (maxFitness - errorFunction.calculate());
    }

    protected Set<Long> stimuliInputs(Genotype genotype, List<Double> inputs)
    {
        int idx = 0;
        Set<Long> stimulated = MapUtils.createHashSet(genotype.getNeuronsSize());

        for (Neuron neuron : genotype.getInputs())
        {
            neuron.reset();
            NeuronType type = neuron.getType();
            stimulated.add(neuron.getInnovation());

            if (NeuronType.isInput(type)) {
                neuron.impulse(inputs.get(idx));
                idx++;
            } else if (NeuronType.isBias(type)) {
                neuron.impulse(1d);
            }
        }
        return stimulated;
    }

    protected double activate(Neuron neuron, Set<Long> stimulated)
    {
        if (!stimulated.contains(neuron.getInnovation()))
        {
            neuron.reset();
            stimulated.add(neuron.getInnovation());

            for (Synapse synapse : neuron.getInputs())
            {
                if (synapse.isEnabled()) {
                    double activation = activate(synapse.getStart(), stimulated);
                    neuron.impulse(activation * synapse.getWeight());
                }
            }
        }
        return neuron.activate();
    }

    protected double adjustFitness(Genotype genotype, int size) {
        double adjustedFitness = genotype.getFitness() / size;
        genotype.setAdjustedFitness(adjustedFitness);
        return adjustedFitness;
    }


    public List<List<Double>> getInputSet() {
        return inputSet;
    }
    public void setInputSet(List<List<Double>> inputSet) {
        this.inputSet = inputSet;
    }

    public List<List<Double>> getOutputSet() {
        return outputSet;
    }
    public void setOutputSet(List<List<Double>> outputSet) {
        this.outputSet = outputSet;
    }

    public ErrorFunction getErrorFunction() {
        return errorFunction;
    }
    public void setErrorFunction(ErrorFunction errorFunction) {
        this.errorFunction = errorFunction;
    }

}
