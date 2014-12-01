package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.AddSynapseMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class AddSynapseMutationFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<AddSynapseMutation, C>
        implements MutationFactory<AddSynapseMutation, C> {

    private SynapseFactory synapseFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        synapseFactory = new SynapseFactory(configuration);
    }

    @Override
    public AddSynapseMutation create() {
        AddSynapseMutation mutation = new AddSynapseMutation();
        mutation.setRate(configuration.getAddSynapseRate());
        mutation.setSynapseFactory(synapseFactory);
        return mutation;
    }

}
