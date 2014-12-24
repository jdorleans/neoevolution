package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.innovation.SynapseInnovation;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.model.Synapse;
import org.neoevolution.core.operator.mutation.WeightSynapseMutation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public class SynapseFactory<C extends NNConfiguration> implements ConfigurableFactory<Synapse, C> {

    private SynapseInnovation innovation;

    private WeightSynapseMutationFactory<C> weightSynapseMutationFactory;

    private WeightSynapseMutation weightSynapseMutation;


    public SynapseFactory() {
        this.weightSynapseMutationFactory = new WeightSynapseMutationFactory<>();
    }


    @Override
    public void configure(C configuration) {
        this.innovation = configuration.getSynapseInnovation();
        initWeightSynapseMutation(configuration);
    }

    private void initWeightSynapseMutation(C configuration) {
        weightSynapseMutationFactory.configure(configuration);
        weightSynapseMutation = weightSynapseMutationFactory.create();
        weightSynapseMutation.setRate(1d);
    }


    @Override
    public Synapse create() {
        return new Synapse();
    }

    public Synapse create(Neuron start, Neuron end) {
        Synapse synapse = create(start, end, 0d);
        weightSynapseMutation.mutate(synapse);
        return synapse;
    }

    public Synapse create(Neuron start, Neuron end, Double weight)
    {
        Synapse synapse = new Synapse(start, end, weight);
        innovation.innovate(synapse, start, end);
        start.addOutput(synapse);
        end.addInput(synapse);
        return synapse;
    }


    public WeightSynapseMutationFactory<C> getWeightSynapseMutationFactory() {
        return weightSynapseMutationFactory;
    }
    public void setWeightSynapseMutationFactory(WeightSynapseMutationFactory<C> weightSynapseMutationFactory) {
        this.weightSynapseMutationFactory = weightSynapseMutationFactory;
    }

}
