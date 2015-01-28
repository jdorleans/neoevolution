package org.neoevolution.factory.operator.mutation;

import org.neoevolution.core.operator.mutation.AddNeuronMutation;
import org.neoevolution.factory.model.NeuronFactory;
import org.neoevolution.factory.model.SynapseFactory;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
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

    public AddNeuronMutationFactory(NeuronFactory<C> neuronFactory, SynapseFactory<C> synapseFactory) {
        this.neuronFactory = neuronFactory;
        this.synapseFactory = synapseFactory;
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
