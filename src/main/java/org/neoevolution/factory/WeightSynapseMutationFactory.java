package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.WeightSynapseMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class WeightSynapseMutationFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<WeightSynapseMutation, C>
        implements MutationFactory<WeightSynapseMutation, C> {

    @Override
    public WeightSynapseMutation create() {
        WeightSynapseMutation mutation = new WeightSynapseMutation();
        mutation.setRate(configuration.getWeightSynapseRate());
        mutation.setRange(configuration.getWeightRange());
        mutation.setReset(configuration.isWeightSynapseReset());
        return mutation;
    }

}
