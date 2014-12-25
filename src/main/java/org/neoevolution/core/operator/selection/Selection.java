package org.neoevolution.core.operator.selection;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 03/11/14.
 */
public interface Selection {

    Set<Genotype> select(Population population);

}
