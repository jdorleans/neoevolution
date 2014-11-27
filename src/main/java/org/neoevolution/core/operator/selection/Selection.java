package org.neoevolution.core.operator.selection;

import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 03/11/14.
 */
public interface Selection {

    Set<Genotype> select(Population population);

}
