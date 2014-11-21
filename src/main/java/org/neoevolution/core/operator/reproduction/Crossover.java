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
        double totalFitness = population.getFitness();
        int populationSize = configuration.getPopulationSize();
        Set<Genotype> offsprings = new HashSet<>(MapUtils.getSize(populationSize));
        Set<Species> species = population.getSpecies();
        population.setSpecies(new LinkedHashSet<Species>(species.size()));
        population.setBestSpecies(null);
        population.setBestGenotype(null);

        for (Species specie : species)
        {
            int births = calculateBirths(specie, totalFitness, populationSize);

            for (int i = 0; i < births; i++) {
                offsprings.add(reproduce(chooseParents(specie.getGenotypes())));
            }
            if (births > 0) {
                specie.setGenotypes(new LinkedHashSet<Genotype>(births + 1));
                specie.addGenotype(specie.getBestGenotype());
                population.addSpecie(specie);
            }
        }
        return offsprings;
    }

    private int calculateBirths(Species specie, double totalFitness, int populationSize) {
        return (int) ((specie.getFitness() / totalFitness) * populationSize);
    }

    private Parents chooseParents(Set<Genotype> genotypes)
    {
        int size = (int) (genotypes.size() * configuration.getElitismRate());
        size = Math.max(1, size);
        int pos = 0;
        int pos1 = Randomizer.randomInt(size);
        int pos2 = Randomizer.randomInt(size);
        Genotype g1 = null;
        Genotype g2 = null;

        for (Genotype genotype : GenotypeUtils.sortByFitness(genotypes, false))
        {
            if (pos == pos1) {
                g1 = genotype;
            }
            if (pos == pos2) {
                g2 = genotype;
            }
            if (g1 != null && g2 != null) {
                break;
            }
            pos++;
        }
        return new Parents(g1, g2);
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
