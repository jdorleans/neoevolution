package org.neoevolution.core.operator.reproduction;

import org.neoevolution.core.*;
import org.neoevolution.core.factory.GenotypeFactory;
import org.neoevolution.core.operator.AbstractOperation;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

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
    public Genotype reproduce(Parents parents, int generation)
    {
        Genotype dominant = parents.getDominant();
        Genotype offspring = genotypeFactory.createEmpty(generation);

        Map<Long, Neuron> neurons = createNeuronsMap(offspring, dominant.getNeuronsSize());
        Map<Long, Synapse> synapses = cloneDominantGenes(parents, offspring, neurons);
        cloneRecessiveGenes(parents.getHasDominant(), offspring, synapses, neurons);
        return offspring;
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


    private Map<Long, Neuron> createNeuronsMap(Genotype offspring, int size)
    {
        Map<Long, Neuron> neurons = MapUtils.createHashMap(size);
        putGenes(offspring.getInputs(), neurons);
        putGenes(offspring.getOutputs(), neurons);
        return neurons;
    }

    private Map<Long, Synapse> createSynapsesMap(Set<Synapse> synapses) {
        Map<Long, Synapse> map = MapUtils.createHashMap(synapses.size());
        putGenes(synapses, map);
        return map;
    }

    private <T extends Gene> void putGenes(Set<T> genes, Map<Long, T> geneMap) {
        for (T gene : genes) {
            geneMap.put(gene.getInnovation(), gene);
        }
    }

}
