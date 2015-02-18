package org.neoevolution.core.operator.selection;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface Selection {

    List<Genotype> select(Population population);

}
