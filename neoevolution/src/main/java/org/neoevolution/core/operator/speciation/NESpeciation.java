package org.neoevolution.core.operator.speciation;

import org.neoevolution.factory.model.SpeciesFactory;
import org.neoevolution.mvc.model.*;
import org.neoevolution.util.InnovationUtils;
import org.neoevolution.util.MapUtils;

import java.util.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class NESpeciation implements Speciation {

    protected int maxSpeciesSize;

    protected double threshold;

    protected double excessFactor;

    protected double weightFactor;

    protected double compatibilityRate;

    protected SpeciesFactory speciesFactory;


    @Override
    public void speciate(Population population, List<Genotype> genotypes)
    {
        int size = population.getSpecies().size();
        Long generation = population.getGeneration();

        for (Genotype genotype : genotypes)
        {
            if (size >= maxSpeciesSize) {
                while (!speciate(genotype, population)) {
                    updateCompatibilityThreshold(size);
                }
            }
            else if (!speciate(genotype, population)) {
                size++;
                population.addSpecies(speciesFactory.create(genotype, generation));
                updateCompatibilityThreshold(size);
            }
        }
    }

    protected boolean speciate(Genotype genotype, Population population)
    {
        List<Species> species = new ArrayList<>(population.getSpecies());
        Collections.shuffle(species);

        for (Species specie : species)
        {
            if (isCompatible(specie.getBestGenotype(), genotype)) {
                specie.addGenotype(genotype);
                return true;
            }
        }
        return false;
    }

    protected void updateCompatibilityThreshold(int size)
    {
        double rate = calculateCompatibilityRate();

        if (size >= maxSpeciesSize) {
            threshold += rate;
            threshold = Math.min(1, threshold);
        } else {
            threshold -= rate;
            threshold = Math.max(0.01, threshold);
        }
    }

    protected double calculateCompatibilityRate()
    {
        double rate = threshold * compatibilityRate;
        rate = Math.min(0.1, rate);
        rate = Math.max(0.001, rate);
        return rate;
    }

    protected boolean isCompatible(Genotype g1, Genotype g2)  {
        double distance = calculateDistance(g1, g2);
        return (distance <= threshold);
    }

    protected double calculateDistance(Genotype g1, Genotype g2) {
        double excess = calculateExcess(g1, g2);
        double weight = calculateWeight(g1.getSynapses(), g2.getSynapses());
        return Math.min(1, excess + weight);
    }

    // Calculate Excess and Disjoint
    protected double calculateExcess(Genotype g1, Genotype g2) {
        double excess = calculateExcess(g1.getNeurons(), g2.getNeurons());
        excess += calculateExcess(g1.getSynapses(), g2.getSynapses());
        return (excessFactor * excess) / calculateTotalGenes(g1, g2);
    }

    // Calculate Excess and Disjoint
    protected int calculateExcess(Set<? extends Gene> g1, Set<? extends Gene> g2)
    {
        int total = MapUtils.getSize(g1.size() + g2.size());
        Set<Long> innovations = new HashSet<>(total);

        for (Gene gene : g1) {
            innovations.add(gene.getInnovation());
        }
        for (Gene gene : g2) {
            innovations.add(gene.getInnovation());
        }
        total = innovations.size();
        return ((total - g1.size()) + (total - g2.size()));
    }

    protected int calculateTotalGenes(Genotype g1, Genotype g2)  {
        return Math.max(g1.getGenesSize(), g2.getGenesSize());
    }

    protected double calculateWeight(Set<Synapse> synapse1, Set<Synapse> synapse2)
    {
        int similar = 0;
        double weightDiff = 0;
        Map<Long, Synapse> synapses = InnovationUtils.createHashMap(synapse1);

        for (Synapse s2 : synapse2)
        {
            Synapse s1 = synapses.get(s2.getInnovation());

            if (s1 != null) {
                similar++;
                weightDiff += Math.abs(s1.getWeight() - s2.getWeight());
            }
        }
        if (similar > 0) {
            return (weightFactor * (weightDiff / similar));
        }
        return 0;
    }


    public int getMaxSpeciesSize() {
        return maxSpeciesSize;
    }
    public void setMaxSpeciesSize(int maxSpeciesSize) {
        this.maxSpeciesSize = maxSpeciesSize;
    }

    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getCompatibilityRate() {
        return compatibilityRate;
    }
    public void setCompatibilityRate(double compatibilityRate) {
        this.compatibilityRate = compatibilityRate;
    }
    
    public double getExcessFactor() {
        return excessFactor;
    }
    public void setExcessFactor(double excessFactor) {
        this.excessFactor = excessFactor;
    }

    public double getWeightFactor() {
        return weightFactor;
    }
    public void setWeightFactor(double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public SpeciesFactory getSpeciesFactory() {
        return speciesFactory;
    }
    public void setSpeciesFactory(SpeciesFactory speciesFactory) {
        this.speciesFactory = speciesFactory;
    }

}
