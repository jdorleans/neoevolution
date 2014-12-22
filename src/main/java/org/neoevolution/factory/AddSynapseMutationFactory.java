package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.operator.mutation.AddSynapseMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class AddSynapseMutationFactory<C extends NNConfiguration>
        extends AbstractConfigurableFactory<AddSynapseMutation, C>
        implements MutationFactory<AddSynapseMutation, C> {

    private SynapseFactory<C> synapseFactory;


    public AddSynapseMutationFactory() {
        synapseFactory = new SynapseFactory<>();
    }


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        synapseFactory.configure(configuration);
    }

    @Override
    public AddSynapseMutation create() {
        AddSynapseMutation mutation = new AddSynapseMutation();
        mutation.setRate(configuration.getAddSynapseRate());
        mutation.setSynapseFactory(synapseFactory);
        return mutation;
    }


    public SynapseFactory<C> getSynapseFactory() {
        return synapseFactory;
    }
    public void setSynapseFactory(SynapseFactory<C> synapseFactory) {
        this.synapseFactory = synapseFactory;
    }

}
