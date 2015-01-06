package org.neoevolution.util;

import org.neoevolution.mvc.model.AbstractFitnessEntity;

import java.util.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 05/11/14.
 */
public final class FitnessUtils {

    private FitnessUtils() { }


    public static <T extends AbstractFitnessEntity> List<T> sortByFitness(Set<T> entities, final boolean desc)
    {
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T e1, T e2) {
                if (desc) {
                    return e2.getFitness().compareTo(e1.getFitness());
                } else {
                    return e1.getFitness().compareTo(e2.getFitness());
                }
            }
        };
        List<T> sorted = new ArrayList<>(entities);
        Collections.sort(sorted, comparator);
        return sorted;
    }

}
