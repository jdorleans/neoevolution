package org.neoevolution.core.operator;

import org.neoevolution.core.*;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Speciation {

    @Autowired
    private GAConfiguration configuration;

    public void speciate(Population population, List<Genotype> genotypes)
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
        int size = configuration.getPopulationSize() / configuration.getMaxSpeciesSize();
        Species species = new Species(configuration.getGeneration(), size);
        species.addGenotype(genotype);
        population.addSpecie(species);
        updateCompatibilityThreshold(population);
    }

    // FIXME - IS IT POSSIBLE TO HAVE NEGATIVE WEIGHT VALUES? HOW TO DEAL WITH THAT?
    private void updateCompatibilityThreshold(Population population)
    {
        int maxSpecies = configuration.getMaxSpeciesSize();
        double threshold = configuration.getCompatibilityThreshold();
        double rate = (1d - threshold) * configuration.getCompatibilityRate();
        int size = population.getSpecies().size();

        if (size > maxSpecies) {
            threshold += rate;
        } else if (size < maxSpecies) {
            threshold -= rate;
        }
        configuration.setCompatibilityThreshold(threshold);
    }

    private boolean isCompatible(Genotype g1, Genotype g2)  {
        return (calculateDistance(g1, g2) < configuration.getCompatibilityThreshold());
    }

    private double calculateDistance(Genotype g1, Genotype g2) {
        return (calculateExcess(g1, g2) + calculateWeight(g1.getSynapses(), g2.getSynapses()));
    }

    // Calculate Excess and Disjoint
    private double calculateExcess(Genotype g1, Genotype g2)
    {
        double excess = calculateExcess(g1.getNeurons(), g2.getNeurons());
        excess += calculateExcess(g1.getSynapses(), g2.getSynapses());
        excess = (configuration.getExcessFactor() * excess) / calculateTotalGenes(g1, g2);
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
        int totalG1 = g1.getNeurons().size() + g1.getSynapses().size();
        int totalG2 = g1.getNeurons().size() + g1.getSynapses().size();
        return Math.max(totalG1, totalG2);
    }

    // FIXME - IS IT POSSIBLE TO HAVE NEGATIVE WEIGHT VALUES? HOW TO DEAL WITH THAT?
    private double calculateWeight(Set<Synapse> synapse1, Set<Synapse> synapse2)
    {
        int similar = 0;
        double average = 0;

        for (Synapse s1 : synapse1)
        {
            for (Synapse s2 : synapse2)
            {
                if (s1.getInnovation().equals(s2.getInnovation())) {
                    similar++;
                    average += Math.abs(s1.getWeight() - s2.getWeight());
                }
            }
        }
        return (configuration.getWeightFactor() * (average / similar));
    }
}
