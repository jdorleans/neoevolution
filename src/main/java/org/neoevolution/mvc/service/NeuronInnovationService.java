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
public class NeuronInnovationService extends AbstractInnovationService<NeuronInnovation, NeuronInnovationRepository> {

    @Autowired
    public NeuronInnovationService(NeuronInnovationRepository repository) {
        super(repository);
    }

    @Override
    public NeuronInnovation create(Long configId)  {
        NeuronInnovation innovation = new NeuronInnovation();
        innovation.setConfigId(configId);
        repository.save(innovation);
        return innovation;
    }

}
