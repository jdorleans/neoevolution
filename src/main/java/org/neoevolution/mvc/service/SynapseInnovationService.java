package org.neoevolution.mvc.service;

import org.neoevolution.core.innovation.SynapseInnovation;
import org.neoevolution.mvc.repository.SynapseInnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
@Service
public class SynapseInnovationService {

    @Autowired
    private SynapseInnovationRepository repository;


    public SynapseInnovation findByConfigIdOrCreate(Long id)
    {
        SynapseInnovation innovation = repository.findByConfigId(id);

        if (innovation == null) {
            innovation = new SynapseInnovation();
            repository.save(innovation);
        }
        return innovation;
    }

}
