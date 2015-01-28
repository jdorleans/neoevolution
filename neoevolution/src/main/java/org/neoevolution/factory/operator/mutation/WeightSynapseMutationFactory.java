package org.neoevolution.factory.operator.mutation;

import org.neoevolution.core.operator.mutation.WeightSynapseMutation;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
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
