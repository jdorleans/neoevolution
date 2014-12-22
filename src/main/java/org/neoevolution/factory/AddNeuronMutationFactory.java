package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.operator.mutation.AddNeuronMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class AddNeuronMutationFactory<C extends NNConfiguration>
        extends AbstractConfigurableFactory<AddNeuronMutation, C>
        implements MutationFactory<AddNeuronMutation, C> {

    private NeuronFactory<C> neuronFactory;

    private SynapseFactory<C> synapseFactory;


    public AddNeuronMutationFactory() {
        neuronFactory = new NeuronFactory<>();
        synapseFactory = new SynapseFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        neuronFactory.configure(configuration);
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


    public NeuronFactory<C> getNeuronFactory() {
        return neuronFactory;
    }
    public void setNeuronFactory(NeuronFactory<C> neuronFactory) {
        this.neuronFactory = neuronFactory;
    }

    public SynapseFactory<C> getSynapseFactory() {
        return synapseFactory;
    }
    public void setSynapseFactory(SynapseFactory<C> synapseFactory) {
        this.synapseFactory = synapseFactory;
    }

}
