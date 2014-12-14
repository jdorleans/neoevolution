package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.AddNeuronMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class AddNeuronMutationFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<AddNeuronMutation, C>
        implements MutationFactory<AddNeuronMutation, C> {

    private NeuronFactory<C> neuronFactory;

    private SynapseFactory<C> synapseFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        neuronFactory = new NeuronFactory<>();
        neuronFactory.configure(configuration);
        synapseFactory = new SynapseFactory<>();
        synapseFactory.configure(configuration);
    }

    @Override
    public AddNeuronMutation create() {
        AddNeuronMutation mutation = new AddNeuronMutation();
        mutation.setRate(configuration.getAddNeuronRate());
        mutation.setMaxHidden(configuration.getHiddenMaxSize());
        mutation.setNeuronFactory(neuronFactory);
        mutation.setSynapseFactory(synapseFactory);
        return mutation;
    }

}