package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.ComposedMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class ComposedMutationFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<ComposedMutation, C>
        implements MutationFactory<ComposedMutation, C> {

    @Override
    public void configure(C configuration) {
        super.configure(configuration);
    }

    @Override
    public ComposedMutation create() {
        return new ComposedMutation();
    }

}
