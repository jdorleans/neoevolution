package org.neoevolution.mvc.service;

import org.neoevolution.core.innovation.NeuronInnovation;
import org.neoevolution.mvc.repository.NeuronInnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
@Service
public class NeuronInnovationService {

    @Autowired
    private NeuronInnovationRepository repository;

    private Map<Long, NeuronInnovation> innovations;


    public NeuronInnovationService() {
        innovations = new HashMap<>();
    }


    public NeuronInnovation findByConfigIdOrCreate(Long id)
    {
        NeuronInnovation innovation = innovations.get(id);

        if (innovation == null)
        {
            innovation = repository.findByConfigId(id);

            if (innovation == null) {
                innovation = new NeuronInnovation();
                innovation.setConfigId(id);
                repository.save(innovation);
            }
            innovations.put(id, innovation);
        }
        return innovation;
    }

}
