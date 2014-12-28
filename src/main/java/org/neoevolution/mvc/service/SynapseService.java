package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.mvc.repository.SynapseRepository;
import org.neoevolution.util.InnovationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    protected void beforeSave(Synapse entity, boolean updateReference) {
//        if (updateReference) {
//            FIXME - FIND IS CREATING A NEW INSTANCE
//            entity.setStart(neuronService.find(entity.getStart()));
//            entity.setEnd(neuronService.find(entity.getEnd()));
//        }
    }

    @Override
    protected void afterSave(Synapse entity, boolean updateReference) {
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
