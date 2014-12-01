package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.innovation.SynapseInnovation;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.model.Synapse;
import org.neoevolution.core.operator.mutation.WeightSynapseMutation;
import org.neoevolution.mvc.service.SynapseInnovationService;
import org.neoevolution.util.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
@Configurable(preConstruction = true)
public class SynapseFactory {

    private SynapseInnovation innovation;

    @Autowired
    private SynapseInnovationService innovationService;

    private WeightSynapseMutation weightSynapseMutation;


    public SynapseFactory(GAConfiguration configuration) {
        this.innovation = innovationService.findByConfigIdOrCreate(configuration.getId());
        initWeightSynapseMutation(configuration);
    }

    private void initWeightSynapseMutation(GAConfiguration configuration) {
        weightSynapseMutation = ClassUtils.create(configuration.getWeightSynapseFunction());
        weightSynapseMutation.setRate(1d);
        weightSynapseMutation.setRange(configuration.getWeightRange());
        weightSynapseMutation.setReset(configuration.isWeightSynapseReset());
    }


    public Synapse create(Neuron start, Neuron end) {
        Synapse synapse = create(start, end, 0d);
        weightSynapseMutation.mutate(synapse);
        return synapse;
    }

    public Synapse create(Neuron start, Neuron end, Double weight)
    {
        Synapse synapse = new Synapse(start, end, weight, true);
        innovation.innovate(synapse, start, end);
        start.addOutput(synapse);
        end.addInput(synapse);
        return synapse;
    }

}
