package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.mvc.repository.NeuronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class NeuronService extends AbstractInnovationEntityService<Neuron, NeuronRepository> {

    @Autowired
    public NeuronService(NeuronRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void save(Neuron entity, boolean updateReference) {
        Set<Synapse> inputs = entity.getInputs();
        Set<Synapse> outputs = entity.getOutputs();
        entity.setInputs(null);
        entity.setOutputs(null);
        repository.save(entity);
        entity.setInputs(inputs);
        entity.setOutputs(outputs);
    }

}
