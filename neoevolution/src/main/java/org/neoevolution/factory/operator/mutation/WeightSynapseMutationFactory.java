package org.neoevolution.factory.operator.mutation;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.core.operator.mutation.WeightSynapseMutation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class WeightSynapseMutationFactory<C extends NNConfiguration>
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
