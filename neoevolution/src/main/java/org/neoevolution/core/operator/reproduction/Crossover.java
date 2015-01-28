package org.neoevolution.core.operator.reproduction;

import org.neoevolution.factory.model.GenotypeFactory;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.util.InnovationUtils;
import org.neoevolution.util.MapUtils;
import org.neoevolution.util.Randomizer;

import java.util.Map;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class Crossover implements Reproduction {

    private double enableSynapseRate;

    private double enableSynapsePenalty;

    private GenotypeFactory genotypeFactory;


    @Override
    public Genotype reproduce(Parents parents, Long generation)
    {
        Genotype dominant = parents.getDominant();
        Genotype offspring = genotypeFactory.createEmpty(generation);

        Map<Long, Neuron> neurons = createNeuronsMap(offspring, dominant.getNeuronsSize());
        Map<Long, Synapse> synapses = cloneDominantGenes(parents, offspring, neurons);
        cloneRecessiveGenes(parents, offspring, synapses, neurons);
        return offspring;
    }

    private Map<Long, Synapse> cloneDominantGenes(Parents parents, Genotype offspring, Map<Long, Neuron> neurons)
    {
        Map<Long, Synapse> recessiveSynapses = createSynapsesMap(parents.getRecessive().getSynapses());

        for (Synapse s1 : parents.getDominant().getSynapses())
        {
            Synapse synapse = s1;
            double rate = enableSynapseRate;
            Long innovation = s1.getInnovation();
            Synapse s2 = recessiveSynapses.get(innovation);

            if (s2 != null) {
                rate = calculateRate(s1, s2);
                synapse = Randomizer.random(s1, s2);
                recessiveSynapses.remove(innovation);
            }
            clone(synapse, neurons, offspring, rate); // excess or disjoint
        }
        return recessiveSynapses;
    }

    private double calculateRate(Synapse s1, Synapse s2)
    {
        if (!s1.isEnabled() && !s2.isEnabled()) {
            return enableSynapseRate / enableSynapsePenalty;
        }
        return enableSynapseRate;
    }

    private void cloneRecessiveGenes(Parents parents, Genotype offspring, Map<Long, Synapse> synapses, Map<Long, Neuron> neurons)
    {
        if (!parents.isEquals() && !parents.getHasDominant()) {
            for (Synapse s2 : synapses.values()) {
                clone(s2, neurons, offspring, enableSynapseRate); // excess or disjoint
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
        InnovationUtils.putMap(offspring.getInputs(), neurons);
        InnovationUtils.putMap(offspring.getOutputs(), neurons);
        return neurons;
    }

    private Map<Long, Synapse> createSynapsesMap(Set<Synapse> synapses) {
        return InnovationUtils.createHashMap(synapses);
    }


    public double getEnableSynapseRate() {
        return enableSynapseRate;
    }
    public void setEnableSynapseRate(double enableSynapseRate) {
        this.enableSynapseRate = enableSynapseRate;
    }

    public double getEnableSynapsePenalty() {
        return enableSynapsePenalty;
    }
    public void setEnableSynapsePenalty(double enableSynapsePenalty) {
        this.enableSynapsePenalty = enableSynapsePenalty;
    }

    public GenotypeFactory getGenotypeFactory() {
        return genotypeFactory;
    }
    public void setGenotypeFactory(GenotypeFactory genotypeFactory) {
        this.genotypeFactory = genotypeFactory;
    }

}
