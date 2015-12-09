package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.repository.NeuronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class NeuronService extends AbstractInnovationEntityService<Neuron, NeuronRepository> {

    @Autowired
    public NeuronService(NeuronRepository repository) {
        super(repository);
    }

    @Override
    protected void afterCreate(Neuron entity, boolean updateReference) { }

    @Override
    protected void beforeUpdate(Neuron entity, Neuron dbEntity) {
        entity.setInputs(dbEntity.getInputs());
        entity.setOutputs(dbEntity.getOutputs());
    }

}
