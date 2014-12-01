package org.neoevolution.mvc.service;

import org.neoevolution.core.innovation.NeuronInnovation;
import org.neoevolution.mvc.repository.NeuronInnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
@Service
public class NeuronInnovationService {

    @Autowired
    private NeuronInnovationRepository repository;


    public NeuronInnovation findByConfigIdOrCreate(Long id)
    {
        NeuronInnovation innovation = repository.findByConfigId(id);

        if (innovation == null) {
            innovation = new NeuronInnovation();
            repository.save(innovation);
        }
        return innovation;
    }

}
