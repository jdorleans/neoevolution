package org.neoevolution.factory;

import org.neoevolution.core.configuration.NEConfiguration;
import org.neoevolution.core.operator.mutation.ComposedMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class NEMutationFactory<C extends NEConfiguration>
        extends AbstractConfigurableFactory<ComposedMutation, C>
        implements MutationFactory<ComposedMutation, C> {

    private AddNeuronMutationFactory<C> addNeuronMutationFactory;

    private AddSynapseMutationFactory<C> addSynapseMutationFactory;

    private WeightSynapseMutationFactory<C> weightSynapseMutationFactory;


    public NEMutationFactory() {
        addNeuronMutationFactory = new AddNeuronMutationFactory<>();
        addSynapseMutationFactory = new AddSynapseMutationFactory<>();
        weightSynapseMutationFactory = new WeightSynapseMutationFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        addNeuronMutationFactory.configure(configuration);
        addSynapseMutationFactory.configure(configuration);
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


    public AddNeuronMutationFactory<C> getAddNeuronMutationFactory() {
        return addNeuronMutationFactory;
    }
    public void setAddNeuronMutationFactory(AddNeuronMutationFactory<C> addNeuronMutationFactory) {
        this.addNeuronMutationFactory = addNeuronMutationFactory;
    }

    public AddSynapseMutationFactory<C> getAddSynapseMutationFactory() {
        return addSynapseMutationFactory;
    }
    public void setAddSynapseMutationFactory(AddSynapseMutationFactory<C> addSynapseMutationFactory) {
        this.addSynapseMutationFactory = addSynapseMutationFactory;
    }

    public WeightSynapseMutationFactory<C> getWeightSynapseMutationFactory() {
        return weightSynapseMutationFactory;
    }
    public void setWeightSynapseMutationFactory(WeightSynapseMutationFactory<C> weightSynapseMutationFactory) {
        this.weightSynapseMutationFactory = weightSynapseMutationFactory;
    }

}