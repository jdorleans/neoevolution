package org.neoevolution.xor.mvc.service;

import org.neoevolution.mvc.service.NEEvolutionService;
import org.neoevolution.xor.factory.XORAlgorithmFactory;
import org.neoevolution.xor.mvc.model.XORConfiguration;
import org.neoevolution.xor.mvc.model.XOREvolution;
import org.neoevolution.xor.mvc.repository.XOREvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
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
