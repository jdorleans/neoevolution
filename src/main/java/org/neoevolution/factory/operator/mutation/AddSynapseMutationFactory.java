package org.neoevolution.factory.operator.mutation;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.core.operator.mutation.AddSynapseMutation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.model.SynapseFactory;

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
