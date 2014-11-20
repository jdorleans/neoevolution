package org.neoevolution.core.operator.reproduction;

import org.neoevolution.core.*;
import org.neoevolution.core.factory.GenotypeFactory;
import org.neoevolution.core.operator.AbstractOperation;
import org.neoevolution.util.GenotypeUtils;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 25/10/14.
 */
@Component
public class Crossover extends AbstractOperation implements Reproduction {

    @Autowired
    private GenotypeFactory genotypeFactory;

    @Autowired
    private GAConfiguration configuration;

    @Override
    protected void initRate() {
        rate = configuration.getReproductionRate();
    }

    @Override
    public Set<Genotype> reproduce(Population population)
    {
        Set<Genotype> offsprings = new HashSet<>(getOffspringSize());

        for (Species species : population.getSpecies())
        {
            Set<Genotype> genotypes = species.getGenotypes();
            int size = genotypes.size();
            int maxSize = species.getMaxSize();

            if (size < maxSize)
            {
                int births = maxSize - size;

                for (int i = 0; i < births; i++) {
                    offsprings.add(reproduce(chooseParents(genotypes)));
                }
            }
        }
        return offsprings;
    }

    private int getOffspringSize() {
        int size = configuration.getPopulationSize();
        return Math.max(1, (int) (size - (size * configuration.getSurvivalRate())));
    }

    private Parents chooseParents(Set<Genotype> genotypes)
    {
        List<Genotype> parents = GenotypeUtils.sortByFitness(genotypes, true);
        int pos1 = 0;
        int pos2 = 0;
        int size = getParentsSize(parents.size());

        if (size > 1)
        {
            pos1 = Randomizer.randomInt(size);
            do {
                pos2 = Randomizer.randomInt(size);
            } while (pos1 == pos2);
        }
        return new Parents(parents.get(pos1), parents.get(pos2));
    }

    private int getParentsSize(int size) {
        int minSize = Math.min(2, size);
        int parents = (int) (size * configuration.getElitismRate());
        return Math.max(minSize, parents);
    }

    private Genotype reproduce(Parents parents)
    {
        Genotype dominant = parents.getDominant();
        Genotype offspring = genotypeFactory.createEmpty();

        Map<Long, Neuron> neurons = createNeuronsMap(offspring, dominant.getNeuronsSize());
        Map<Long, Synapse> synapses = cloneDominantGenes(parents, offspring, neurons);
        cloneRecessiveGenes(parents.getHasDominant(), offspring, synapses, neurons);
        return offspring;
    }

    private Map<Long, Neuron> createNeuronsMap(Genotype offspring, int size)
    {
        Map<Long, Neuron> neurons = new HashMap<>(MapUtils.getSize(size));
        putNeurons(offspring.getInputs(), neurons);
        putNeurons(offspring.getOutputs(), neurons);
        return neurons;
    }

    private void putNeurons(Set<Neuron> neurons, Map<Long, Neuron> neuronsMap) {
        for (Neuron neuron : neurons) {
            neuronsMap.put(neuron.getInnovation(), neuron);
        }
    }

    private Map<Long, Synapse> cloneDominantGenes(Parents parents, Genotype offspring, Map<Long, Neuron> neurons)
    {
        double enableRate = configuration.getEnableSynapseRate();
        double enablePenalty = configuration.getEnableSynapsePenalty();
        Map<Long, Synapse> recessiveSynapses = createSynapsesMap(parents.getRecessive().getSynapses());

        for (Synapse s1 : parents.getDominant().getSynapses())
        {
            Synapse synapse = s1;
            double rate = enableRate;
            Long innovation = s1.getInnovation();
            Synapse s2 = recessiveSynapses.get(innovation);

            if (s2 != null) {
                rate = calculateRate(s1, s2, enableRate, enablePenalty);
                synapse = Randomizer.random(s1, s2);
                recessiveSynapses.remove(innovation);
            }
            clone(synapse, neurons, offspring, rate); // excess or disjoint
        }
        return recessiveSynapses;
    }

    private double calculateRate(Synapse s1, Synapse s2, double rate, double penalty)
    {
        if (!s1.isEnabled() && !s2.isEnabled()) {
            return rate / penalty;
        }
        return rate;
    }

    private void cloneRecessiveGenes(boolean hasDominant, Genotype offspring, Map<Long, Synapse> synapses, Map<Long, Neuron> neurons)
    {
        if (!hasDominant)
        {
            double enableRate = configuration.getEnableSynapseRate();

            for (Synapse s2 : synapses.values()) {
                clone(s2, neurons, offspring, enableRate); // excess or disjoint
            }
        }
    }

    private Map<Long, Synapse> createSynapsesMap(Set<Synapse> synapses)
    {
        Map<Long, Synapse> map = new HashMap<>(MapUtils.getSize(synapses.size()));

        for (Synapse synapse : synapses) {
            map.put(synapse.getInnovation(), synapse);
        }
        return map;
    }

    private Synapse clone(Synapse synapse, Map<Long, Neuron> neurons, Genotype offspring, double enableRate)
    {
        Synapse s = new Synapse(synapse);
        Neuron neuron = clone(synapse.getStart(), neurons, offspring);
        neuron.addOutput(s);
        s.setStart(neuron);

        neuron = clone(synapse.getEnd(), neurons, offspring);
        neuron.addInput(s);
        s.setEnd(neuron);
        offspring.addSynapse(s);

        if (!s.isEnabled() && Randomizer.randomBoolean(enableRate)) {
            s.setEnabled(true);
        }
        return s;
    }

    private Neuron clone(Neuron neuron, Map<Long, Neuron> neurons, Genotype offspring)
    {
        Long innovation = neuron.getInnovation();
        Neuron n = neurons.get(innovation);

        if (n == null) {
            n = new Neuron(neuron);
            neurons.put(innovation, n);
            offspring.addNeuron(n);
        }
        return n;
    }

}
