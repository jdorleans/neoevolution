package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.*;
import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.error.ErrorFunctionManager;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

public abstract class TrainingEvaluation implements Evaluation {

    public static final double MAX_FITNESS = 1d;

    protected List<List<Double>> inputSet;

    protected List<List<Double>> outputSet;

    protected ErrorFunction errorFunction;

    @Autowired
    protected ErrorFunctionManager errorFunctionManager;


    @PostConstruct
    private void init() {
        errorFunction = errorFunctionManager.get();
    }

    @Override
    public void evaluate(Population population)
    {
        double fitness = 0d;

        for (Species species : population.getSpecies()) {
            fitness += evaluate(species);
            updateBestSpecies(population, species);
        }
        population.setFitness(fitness);
    }

    private double evaluate(Species species)
    {
        double fitness = 0d;
        Set<Genotype> genotypes = species.getGenotypes();
        int size = genotypes.size();

        for (Genotype genotype : genotypes)
        {
            if (!genotype.isEvaluated()) {
                evaluate(genotype);
            }
            updateBestGenotype(species, genotype);
            fitness += adjustFitness(genotype, size);
        }
        species.setFitness(fitness);
        return fitness;
    }

    private void evaluate(Genotype genotype)
    {
        double fitness = 0d;
        int evaluations = inputSet.size();

        for (int idx = 0; idx < evaluations; idx++) {
            fitness += evaluate(genotype, inputSet.get(idx), outputSet.get(idx));
        }
        genotype.setFitness(fitness / evaluations);
        genotype.setEvaluated(true);
    }

    private double evaluate(Genotype genotype, List<Double> inputs, List<Double> outputs)
    {
        errorFunction.reset();
        Set<Long> stimulated = stimuliInputs(genotype, inputs);

        int idx = 0;
        for (Neuron neuron : genotype.getOutputs()) {
            double activation = activate(neuron, stimulated);
            errorFunction.add(outputs.get(idx), activation);
            idx++;
        }
        return (MAX_FITNESS - errorFunction.calculate());
    }

    private Set<Long> stimuliInputs(Genotype genotype, List<Double> inputs)
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

    private double activate(Neuron neuron, Set<Long> stimulated)
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

    private double adjustFitness(Genotype genotype, int size) {
        double adjustedFitness = genotype.getFitness() / size;
        genotype.setAdjustedFitness(adjustedFitness);
        return adjustedFitness;
    }

    private void updateBestGenotype(Species species, Genotype genotype) {
        if (genotype.getFitness() > species.getBestGenotype().getFitness()) {
            species.setBestGenotype(genotype);
        }
    }

    private void updateBestSpecies(Population population, Species species)
    {
        Genotype bestGenotype = species.getBestGenotype();

        if (bestGenotype.getFitness() > population.getBestGenotype().getFitness()) {
            population.setBestGenotype(bestGenotype);
        }
        if (species.getFitness() > population.getBestSpecies().getFitness()) {
            population.setBestSpecies(species);
        }
    }

}
