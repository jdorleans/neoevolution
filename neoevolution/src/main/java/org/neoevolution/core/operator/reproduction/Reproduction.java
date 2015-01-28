package org.neoevolution.core.operator.reproduction;

import org.neoevolution.mvc.model.Genotype;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface Reproduction {

    Genotype reproduce(Parents parents, Long generation);

}
