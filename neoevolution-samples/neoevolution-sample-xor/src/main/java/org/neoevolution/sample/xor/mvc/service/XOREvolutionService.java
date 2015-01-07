package org.neoevolution.sample.xor.mvc.service;

import org.neoevolution.mvc.service.NEEvolutionService;
import org.neoevolution.sample.xor.factory.XORAlgorithmFactory;
import org.neoevolution.sample.xor.mvc.model.XORConfiguration;
import org.neoevolution.sample.xor.mvc.model.XOREvolution;
import org.neoevolution.sample.xor.mvc.repository.XOREvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class XOREvolutionService extends NEEvolutionService<XOREvolution, XORConfiguration, XOREvolutionRepository> {

    @Autowired
    protected XOREvolutionService(XOREvolutionRepository repository, XORConfigurationService configurationService) {
        super(repository, configurationService);
    }

    @Override
    protected XOREvolution create() {
        return new XOREvolution();
    }

    @Override
    protected XORAlgorithmFactory createFactory() {
        return new XORAlgorithmFactory();
    }

}
