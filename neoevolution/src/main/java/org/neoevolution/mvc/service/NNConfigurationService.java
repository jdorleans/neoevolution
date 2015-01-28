package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.repository.NNConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class NNConfigurationService<T extends NNConfiguration, R extends NNConfigurationRepository<T>>
        extends AbstractService<T, R> {

    @Autowired
    protected NeuronInnovationService neuronInnovationService;

    @Autowired
    protected SynapseInnovationService synapseInnovationService;


    protected NNConfigurationService(R repository) {
        super(repository);
    }


    @Override
    protected void beforeCreate(T entity, boolean updateReference) {
        neuronInnovationService.create(entity.getNeuronInnovation(), updateReference);
        synapseInnovationService.create(entity.getSynapseInnovation(), updateReference);
    }

    @Override
    protected void beforeUpdate(T entity, T dbEntity) {
        entity.setNeuronInnovation(dbEntity.getNeuronInnovation());
        entity.setSynapseInnovation(dbEntity.getSynapseInnovation());
    }

}
