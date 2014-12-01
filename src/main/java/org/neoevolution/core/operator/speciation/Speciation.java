package org.neoevolution.core.operator.speciation;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.model.*;
import org.neoevolution.util.MapUtils;

import java.util.HashSet;
import java.util.Set;

public class Speciation {

    private int populationSize;

    private int maxSpeciesSize;

    private double threshold;

    private double excessFactor;

    private double weightFactor;

    private double compatibilityRate;


    public Speciation() { }


    public void configure(GAConfiguration configuration)
    {
        populationSize = configuration.getPopulationSize();
        maxSpeciesSize = configuration.getMaxSpeciesSize();
        threshold = configuration.getCompatibilityThreshold();
        excessFactor = configuration.getExcessFactor();
        weightFactor = configuration.getWeightFactor();
        compatibilityRate = configuration.getCompatibilityRate();
    }


    public void speciate(Population population, Set<Genotype> genotypes)
    {
        for (Genotype genotype : genotypes) {
            if (!speciate(genotype, population)) {
                createNewSpecie(genotype, population);
            }
        }
    }

    private boolean speciate(Genotype genotype, Population population)
    {
        boolean found = false;

        for (Species species : population.getSpecies())
        {
            if (isCompatible(species.getBestGenotype(), genotype)) {
                found = true;
                species.addGenotype(genotype);
                break;
            }
        }
        return found;
    }

    private void createNewSpecie(Genotype genotype, Population population)
    {
        int size = Math.max(1, populationSize / maxSpeciesSize);
        Species species = new Species(population.getGeneration(), size);
        species.addGenotype(genotype);
        population.addSpecie(species);
        updateCompatibilityThreshold(population);
    }

    private void updateCompatibilityThreshold(Population population)
    {
        int size = population.getSpecies().size();
        double rate = threshold * compatibilityRate;

        if (size > maxSpeciesSize) {
            threshold += rate;
        } else if (size < maxSpeciesSize) {
            threshold -= rate;
        }
    }

    private boolean isCompatible(Genotype g1, Genotype g2)  {
        double distance = calculateDistance(g1, g2);
        return (distance < threshold);
    }

    private double calculateDistance(Genotype g1, Genotype g2) {
        double excess = calculateExcess(g1, g2);
        double weight = calculateWeight(g1.getSynapses(), g2.getSynapses());
        return (excess + weight);
    }

    // Calculate Excess and Disjoint
    private double calculateExcess(Genotype g1, Genotype g2)
    {
        double excess = calculateExcess(g1.getNeurons(), g2.getNeurons());
        excess += calculateExcess(g1.getSynapses(), g2.getSynapses());
        excess = excessFactor * excess;
//        excess = (configuration.getExcessFactor() * excess) / calculateTotalGenes(g1, g2); // TODO - SHOULD DIVIDE?
        return excess;
    }

    // Calculate Excess and Disjoint
    private int calculateExcess(Set<? extends Gene> g1, Set<? extends Gene> g2)
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

    private int calculateTotalGenes(Genotype g1, Genotype g2)  {
        return Math.max(g1.getGenesSize(), g2.getGenesSize());
    }

    private double calculateWeight(Set<Synapse> synapse1, Set<Synapse> synapse2)
    {
        int similar = 0;
        double weightDiff = 0;

        for (Synapse s1 : synapse1)
        {
            for (Synapse s2 : synapse2)
            {
                if (s1.getInnovation().equals(s2.getInnovation())) {
                    similar++;
                    weightDiff += Math.abs(s1.getWeight() - s2.getWeight());
                    break;
                }
            }
        }
        return (weightFactor * (weightDiff / similar));
    }

}
