package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.Mutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface MutationFactory<T extends Mutation, C extends GAConfiguration>
        extends ConfigurableFactory<T, C> {
}
