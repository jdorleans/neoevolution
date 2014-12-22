package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.operator.mutation.Mutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface MutationFactory<T extends Mutation, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {
}
