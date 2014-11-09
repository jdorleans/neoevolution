package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.*;
import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.core.error.ErrorFunctionManager;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TrainingEvaluation implements Evaluation {

    private List<Double> inputs;

    private List<Double> outputs;

    @Autowired
    private ErrorFunctionManager errorFunctionManager;


    public TrainingEvaluation() {
        inputs = new ArrayList<>();
        inputs.add(1d);
        inputs.add(1d);
        outputs = new ArrayList<>();
        outputs.add(0d);
    }

    public TrainingEvaluation(List<Double> inputs, List<Double> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }


    public void evaluate(Population population)
    {
        double totalFitness = 0;

        for (Species species : population.getSpecies())
        {
            if (!species.isEvaluated())
            {
                Set<Genotype> genotypes = species.getGenotypes();
                int size = genotypes.size();
                double speciesFitness = 0;

                for (Genotype genotype : genotypes)
                {
                    if (!genotype.isEvaluated()) {
                        evaluate(genotype);
                        speciesFitness += adjustFitness(genotype, size);
                        updateBestGenotype(population, species, genotype);
                    }
                }
                speciesFitness /= size;
                totalFitness += speciesFitness;
                species.setFitness(speciesFitness);
                updateBestSpecies(population, species);
                species.setEvaluated(true);
            }
        }
        population.setFitness(totalFitness);
    }

    private void evaluate(Genotype genotype)
    {
        Map<Neuron, Double> neurons = activateInputs(genotype);
        int size = genotype.getNeurons().size() - genotype.getInputs().size();
        Set<Long> activated = new HashSet<>(MapUtils.getSize(size));

        // Should not have disconnected neurons (except input neurons)
        while(activated.size() < size)
        {
            Map<Neuron, Double> nextNeurons = new HashMap<>();

            for (Neuron neuron : neurons.keySet()) {
                neuron.activate(neurons.get(neuron));
                propagate(neuron, nextNeurons);
                activated.add(neuron.getInnovation());
            }
            neurons = nextNeurons;
        }
        calculateFitness(genotype);
        genotype.setEvaluated(true);
    }

    private Map<Neuron, Double> activateInputs(Genotype genotype)
    {
        Map<Neuron, Double> nextNeurons = new HashMap<>();

        int idx = 0;
        for (Neuron neuron : genotype.getInputs()) {
            activateInput(idx, neuron);
            propagate(neuron, nextNeurons);
            idx++;
        }
        return nextNeurons;
    }

    private void activateInput(int idx, Neuron neuron)
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
        Double activation = start.getActivation();

        for (Synapse output : start.getOutputs())
        {
            if (output.isEnabled())
            {
                Neuron end = output.getEnd();
                Double value = neurons.get(end);

                if (value == null) {
                    value = 0d;
                }
                value += activation * output.getWeight();
                neurons.put(end, value);
            }
        }
    }

    // TODO - Should we test only the last output impulse?
    // TODO - How to deal with output impulses at different moment of time?
    private void calculateFitness(Genotype genotype)
    {
        ErrorFunction errorFunction = errorFunctionManager.get();

        int idx = 0;
        for (Neuron neuron : genotype.getOutputs()) {
            errorFunction.add(outputs.get(idx), neuron.getActivation());
            idx++;
        }
        double fitness = 1d - errorFunction.calculate();
        genotype.setFitness(fitness);
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
