package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.innovation.NeuronInnovation;
import org.neoevolution.mvc.repository.NeuronInnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class NeuronInnovationService extends AbstractInnovationService<NeuronInnovation, NeuronInnovationRepository> {

    @Autowired
    public NeuronInnovationService(NeuronInnovationRepository repository) {
        super(repository);
    }

}
