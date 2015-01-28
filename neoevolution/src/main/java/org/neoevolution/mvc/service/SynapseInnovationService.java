package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.innovation.SynapseInnovation;
import org.neoevolution.mvc.repository.SynapseInnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class SynapseInnovationService extends AbstractInnovationService<SynapseInnovation, SynapseInnovationRepository> {

    @Autowired
    public SynapseInnovationService(SynapseInnovationRepository repository) {
        super(repository);
    }

}
