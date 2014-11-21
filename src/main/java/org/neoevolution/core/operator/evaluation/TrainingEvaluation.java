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
                }
                updateBestGenotype(species, genotype);
                speciesFitness += adjustFitness(genotype, size);
            }
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
            int size = MapUtils.getSize(genotype.getNeuronsSize());
            Map<Neuron, Double> neurons = new HashMap<>(size);
            Set<Long> activated = activateInputs(genotype, inputSet.get(idx), neurons, activations);

            while (activated.size() < activations)
            {
                Map<Neuron, Double> nextNeurons = new HashMap<>(size);

                for (Neuron neuron : neurons.keySet()) {
                    neuron.activate(neurons.get(neuron));
                    activated.addAll(propagate(neuron, nextNeurons));
                }
                neurons = nextNeurons;
            }
            for (Neuron neuron : neurons.keySet()) {
                neuron.activate(neurons.get(neuron));
            }
            fitness += calculateFitness(genotype, outputSet.get(idx));
        }
        genotype.setFitness(fitness / evaluations);
        genotype.setEvaluated(true);
    }

    private int getActivationSize(Genotype genotype)
    {
        int size = 0;

        for (Neuron neuron : genotype.getOutputs()) {
            size += getActiveInputSize(neuron);
        }
        return size;
    }

    private int getActiveInputSize(Neuron neuron)
    {
        int size = 0;

        for (Synapse synapse : neuron.getInputs()) {
            if (synapse.isEnabled()) {
                size++;
            }
        }
        return size;
    }

    private Set<Long> activateInputs(Genotype genotype, List<Double> inputs, Map<Neuron, Double> neurons, int activations)
    {
        int idx = 0;
        Set<Long> activated = new HashSet<>(MapUtils.getSize(activations));

        for (Neuron neuron : genotype.getInputs()) {
            idx = activateInput(idx, inputs, neuron);
            activated.addAll(propagate(neuron, neurons));
        }
        return activated;
    }

    private int activateInput(int idx, List<Double> inputs, Neuron neuron)
    {
        NeuronType type = neuron.getType();

        if (NeuronType.isInput(type)) {
            neuron.activate(inputs.get(idx));
            idx++;
        } else if (NeuronType.isBias(type)) {
            neuron.activate(1d);
        }
        return idx;
    }

    private Set<Long> propagate(Neuron start, Map<Neuron, Double> neurons)
    {
        Double activation = start.getActivation();
        Set<Synapse> outputs = start.getOutputs();
        Set<Long> activated = new HashSet<>(MapUtils.getSize(outputs.size()));

        for (Synapse output : outputs)
        {
            if (output.isEnabled())
            {
                Neuron end = output.getEnd();
                Double value = NumberUtils.getNotNull(neurons.get(end));
                value += activation * output.getWeight();
                neurons.put(end, value);

                if (NeuronType.isOutput(end.getType())) {
                    activated.add(output.getInnovation());
                }
            }
        }
        return activated;
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
