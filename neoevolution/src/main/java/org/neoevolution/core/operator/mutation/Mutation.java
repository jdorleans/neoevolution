package org.neoevolution.core.operator.mutation;

import org.neoevolution.mvc.model.Genotype;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface Mutation {

    void mutate(Genotype genotype);

}
