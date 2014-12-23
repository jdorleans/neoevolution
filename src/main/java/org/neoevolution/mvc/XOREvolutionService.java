package org.neoevolution.mvc;

import org.neoevolution.core.configuration.XORConfiguration;
import org.neoevolution.factory.XORAlgorithmFactory;
import org.neoevolution.mvc.repository.XOREvolutionRepository;
import org.neoevolution.mvc.service.XORConfigurationService;
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
