package org.neoevolution.core.operator.speciation;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface Speciation {

    void speciate(Population population, List<Genotype> genotypes);

}
