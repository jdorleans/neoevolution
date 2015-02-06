package org.neoevolution.sample.soundfilter.mvc.service;

import org.neoevolution.mvc.service.NEEvolutionService;
import org.neoevolution.sample.soundfilter.factory.SFAlgorithmFactory;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;
import org.neoevolution.sample.soundfilter.mvc.model.SFEvolution;
import org.neoevolution.sample.soundfilter.mvc.repository.SFEvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class SFEvolutionService extends NEEvolutionService<SFEvolution, SFConfiguration, SFEvolutionRepository> {

    @Autowired
    protected SFEvolutionService(SFEvolutionRepository repository, SFConfigurationService configurationService) {
        super(repository, configurationService);
    }

    @Override
    protected SFEvolution create() {
        return new SFEvolution();
    }

    @Override
    protected SFAlgorithmFactory createFactory() {
        return new SFAlgorithmFactory();
    }

}
