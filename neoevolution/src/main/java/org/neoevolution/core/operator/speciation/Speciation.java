package org.neoevolution.core.operator.speciation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 17 2014
 */
public interface Speciation {

    void speciate(Population population, Set<Genotype> genotypes);

}