package org.neoevolution.mvc.service;

import org.neoevolution.core.GAConfiguration;
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

    public NeuronInnovation create(Long configId)  {
        NeuronInnovation innovation = new NeuronInnovation();
        innovation.setConfigId(configId);
        repository.save(innovation);
        return innovation;
    }


    public NeuronInnovation findOrCreate(GAConfiguration configuration)
    {
        Long configId = configuration.getId();
        NeuronInnovation innovation = innovations.get(configId);

        if (innovation == null)
        {
            innovation = repository.findByConfigId(configId);

            if (innovation == null) {
                innovation = create(configId);
            }
            innovations.put(configId, innovation);
        }
        return innovation;
    }

}
