package org.neoevolution.core.operator.selection;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.Population;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 03/11/14.
 */
public interface Selection {

    Set<Genotype> select(Population population);

}
