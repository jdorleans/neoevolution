package org.neoevolution.util;

import org.neoevolution.core.Genotype;

import java.util.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 05/11/14.
 */
public class GenotypeUtils {

    public static List<Genotype> sortByFitness(Set<Genotype> genotypes, final boolean desc)
    {
        Comparator<Genotype> comparator = new Comparator<Genotype>()
        {
            @Override
            public int compare(Genotype g1, Genotype g2) {
                if (desc) {
                    return g2.getFitness().compareTo(g1.getFitness());
                } else {
                    return g1.getFitness().compareTo(g2.getFitness());
                }
            }
        };
        List<Genotype> sorted = new ArrayList<>(genotypes);
        Collections.sort(sorted, comparator);
        return sorted;
    }

}
