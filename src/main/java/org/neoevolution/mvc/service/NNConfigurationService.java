package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.repository.NNConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 14 2014
 */
public abstract class NNConfigurationService<T extends NNConfiguration, R extends NNConfigurationRepository<T>>
        extends AbstractService<T, R> {

    protected Map<Long, T> configurations;

    @Autowired
    protected NeuronInnovationService neuronInnovationService;

    @Autowired
    protected SynapseInnovationService synapseInnovationService;


    protected NNConfigurationService(R repository) {
        super(repository);
    }


    @Override
    protected void beforeSave(T entity) {
        neuronInnovationService.save(entity.getNeuronInnovation());
        synapseInnovationService.save(entity.getSynapseInnovation());
    }

}
