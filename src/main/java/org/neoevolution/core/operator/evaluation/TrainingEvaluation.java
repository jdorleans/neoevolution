package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.*;
import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.error.ErrorFunctionManager;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

public abstract class TrainingEvaluation implements Evaluation {

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
        double totalFitness = 0;

        for (Species species : population.getSpecies())
        {
            Set<Genotype> genotypes = species.getGenotypes();
            int size = genotypes.size();
            double speciesFitness = 0;

            for (Genotype genotype : genotypes)
            {
                if (!genotype.isEvaluated()) {
                    evaluate(genotype);
                    updateBestGenotype(population, species, genotype);
                }
                speciesFitness += adjustFitness(genotype, size);
            }
            speciesFitness /= size;
            totalFitness += speciesFitness;
            species.setFitness(speciesFitness);
            updateBestSpecies(population, species);
        }
        population.setFitness(totalFitness);
    }

    private void evaluate(Genotype genotype)
    {
        double fitness = 0d;
        int evaluations = inputSet.size();
        int activations = getActivationSize(genotype);

        for (int idx = 0; idx < evaluations; idx++)
        {
            Map<Neuron, Double> neurons = activateInputs(genotype, inputSet.get(idx));
            Set<Long> activated = new HashSet<>(MapUtils.getSize(activations));

            // Should not have disconnected neurons (except input neurons)
            while(activated.size() < activations)
            {
                Map<Neuron, Double> nextNeurons = new HashMap<>();

                for (Neuron neuron : neurons.keySet()) {
                    neuron.activate(neurons.get(neuron));
                    propagate(neuron, nextNeurons);
                    activated.add(neuron.getInnovation());
                }
                neurons = nextNeurons;
            }
            fitness += calculateFitness(genotype, outputSet.get(idx));
        }
        genotype.setFitness(fitness / evaluations);
        genotype.setEvaluated(true);
    }

    private int getActivationSize(Genotype genotype)
    {
        Set<Neuron> neurons = genotype.getNeurons();
        int size = neurons.size();

        for (Neuron neuron : neurons) {
            if (!isActive(neuron)) {
                size--;
            }
        }
        return size;
    }

    private boolean isActive(Neuron neuron)
    {
        boolean isActive = false;

        for (Synapse synapse : neuron.getInputs()) {
            if (synapse.isEnabled()) {
                isActive = true;
                break;
            }
        }
        return isActive;
    }

    private Map<Neuron, Double> activateInputs(Genotype genotype, List<Double> inputs)
    {
        Map<Neuron, Double> nextNeurons = new HashMap<>();

        int idx = 0;
        for (Neuron neuron : genotype.getInputs()) {
            activateInput(idx, inputs, neuron);
            propagate(neuron, nextNeurons);
            idx++;
        }
        return nextNeurons;
    }

    private void activateInput(int idx, List<Double> inputs, Neuron neuron)
    {
        NeuronType type = neuron.getType();

        if (NeuronType.isInput(type)) {
            neuron.activate(inputs.get(idx));
        } else if (NeuronType.isBias(type)) {
            neuron.activate(1d);
        }
    }

    private void propagate(Neuron start, Map<Neuron, Double> neurons)
    {
        Double activation = NumberUtils.getNotNull(start.getActivation());

        for (Synapse output : start.getOutputs())
        {
            if (output.isEnabled())
            {
                Neuron end = output.getEnd();
                Double value = NumberUtils.getNotNull(neurons.get(end));
                value += activation * output.getWeight();
                neurons.put(end, value);
            }
        }
    }

    // TODO - Should we test only the last output impulse?
    // TODO - How to deal with output impulses at different moment of time?
    private double calculateFitness(Genotype genotype, List<Double> outputs)
    {
        int idx = 0;
        errorFunction.reset();

        for (Neuron neuron : genotype.getOutputs()) {
            errorFunction.add(outputs.get(idx), neuron.getActivation());
            idx++;
        }
        return (1d - errorFunction.calculate());
    }

    private double adjustFitness(Genotype genotype, int size) {
        double adjustedFitness = genotype.getFitness() / size;
        genotype.setAdjustedFitness(adjustedFitness);
        return adjustedFitness;
    }

    private void updateBestGenotype(Population population, Species species, Genotype genotype)
    {
        Double fitness = genotype.getFitness();

        if (species.getBestGenotype().getFitness() < fitness)
        {
            species.setBestGenotype(genotype);

            if (population.getBestGenotype().getFitness() < fitness) {
                population.setBestGenotype(genotype);
            }
        }
    }

    private void updateBestSpecies(Population population, Species species) {
        if (population.getBestSpecies().getFitness() < species.getFitness()) {
            population.setBestSpecies(species);
        }
    }

}
