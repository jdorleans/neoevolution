package org.neoevolution.factory.operator.mutation;

import org.neoevolution.core.operator.mutation.Mutation;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface MutationFactory<T extends Mutation, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {
}
