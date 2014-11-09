package org.neoevolution.mvc.service;

import org.neoevolution.core.Synapse;
import org.neoevolution.mvc.repository.SynapseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class SynapseService extends AbstractService<Synapse, SynapseRepository> {

    @Autowired
    private NeuronService neuronService;


    @Autowired
    public SynapseService(SynapseRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeSave(Synapse entity) {
        neuronService.save(entity.getStart());
        neuronService.save(entity.getEnd());
    }

}
