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
public class SynapseInnovationService extends AbstractInnovationService<SynapseInnovation, SynapseInnovationRepository> {

    @Autowired
    public SynapseInnovationService(SynapseInnovationRepository repository) {
        super(repository);
    }

    @Override
    public SynapseInnovation create(Long configId) {
        SynapseInnovation innovation = new SynapseInnovation();
        innovation.setConfigId(configId);
        repository.save(innovation);
        return innovation;
    }

}
