package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.mvc.repository.SynapseRepository;
import org.neoevolution.util.InnovationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class SynapseService extends AbstractInnovationEntityService<Synapse, SynapseRepository> {

    @Autowired
    public SynapseService(SynapseRepository repository) {
        super(repository);
    }


    @Override
    protected void afterCreate(Synapse entity, boolean updateReference) {
        if (updateReference) {
            updateSynapse(entity, entity.getStart().getOutputs());
            updateSynapse(entity, entity.getEnd().getInputs());
        }
    }

    private void updateSynapse(Synapse entity, Set<Synapse> synapses) {
        InnovationUtils.remove(entity.getInnovation(), synapses);
        synapses.add(entity);
    }

}
