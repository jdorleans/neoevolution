package org.neoevolution.mvc.service;

import org.neoevolution.core.innovation.SynapseInnovation;
import org.neoevolution.mvc.repository.SynapseInnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
@Service
public class SynapseInnovationService {

    @Autowired
    private SynapseInnovationRepository repository;

    private Map<Long, SynapseInnovation> innovations;


    public SynapseInnovationService() {
        innovations = new HashMap<>();
    }


    public SynapseInnovation findByConfigIdOrCreate(Long id)
    {
        SynapseInnovation innovation = innovations.get(id);

        if (innovation == null)
        {
            innovation = repository.findByConfigId(id);

            if (innovation == null) {
                innovation = new SynapseInnovation();
                innovation.setConfigId(id);
                repository.save(innovation);
            }
            innovations.put(id, innovation);
        }
        return innovation;
    }

}
