package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.ComposedMutation;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class ComposedMutationFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<ComposedMutation, C>
        implements MutationFactory<ComposedMutation, C> {

    private AddNeuronMutationFactory<C> addNeuronMutationFactory;

    private AddSynapseMutationFactory<C> addSynapseMutationFactory;

    private WeightSynapseMutationFactory<C> weightSynapseMutationFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        addNeuronMutationFactory = ClassUtils.create(configuration.getAddNeuronMutationFactory());
        addNeuronMutationFactory.configure(configuration);
        addSynapseMutationFactory = ClassUtils.create(configuration.getAddSynapseMutationFactory());
        addSynapseMutationFactory.configure(configuration);
        weightSynapseMutationFactory = ClassUtils.create(configuration.getWeightSynapseMutationFactory());
        weightSynapseMutationFactory.configure(configuration);
    }

    @Override
    public ComposedMutation create() {
        ComposedMutation mutation = new ComposedMutation();
        mutation.addMutation(addNeuronMutationFactory.create());
        mutation.addMutation(addSynapseMutationFactory.create());
        mutation.addMutation(weightSynapseMutationFactory.create());
        return mutation;
    }

}
