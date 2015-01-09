package org.neoevolution.factory.operator.selection;

import org.neoevolution.core.operator.mutation.Mutation;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.operator.mutation.MutationFactory;
import org.neoevolution.factory.operator.reproduction.ReproductionFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public abstract class AbstractSelectionFactory
        <S extends Selection, R extends Reproduction, M extends Mutation, C extends NNConfiguration>
        extends AbstractConfigurableFactory<S, C>
        implements SelectionFactory<S, C> {

    protected ReproductionFactory<R, C> reproductionFactory;

    protected MutationFactory<M, C> mutationFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        reproductionFactory.configure(configuration);
        mutationFactory.configure(configuration);
    }

}
