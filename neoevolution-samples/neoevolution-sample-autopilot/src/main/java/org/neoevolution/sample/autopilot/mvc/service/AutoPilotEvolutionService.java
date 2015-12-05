package org.neoevolution.sample.autopilot.mvc.service;

import org.neoevolution.mvc.service.NEEvolutionService;
import org.neoevolution.sample.autopilot.AutoPilotNeoEvolution;
import org.neoevolution.sample.autopilot.factory.AutoPilotAlgorithmFactory;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotEvolution;
import org.neoevolution.sample.autopilot.mvc.repository.AutoPilotEvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class AutoPilotEvolutionService extends NEEvolutionService
        <AutoPilotEvolution, AutoPilotConfiguration, AutoPilotEvolutionRepository, AutoPilotAlgorithmFactory> {

    @Autowired
    protected AutoPilotEvolutionService(AutoPilotEvolutionRepository repository, AutoPilotConfigurationService configurationService) {
        super(repository, configurationService);
    }

    @Override
    public AutoPilotEvolution evolve(AutoPilotConfiguration configuration, Boolean create) {
        AutoPilotNeoEvolution.application.setMaxScores(configuration.getBestFitness());
        return super.evolve(configuration, create);
    }

    @Override
    protected AutoPilotEvolution create() {
        return new AutoPilotEvolution();
    }

    @Override
    protected AutoPilotAlgorithmFactory createFactory() {
        return new AutoPilotAlgorithmFactory();
    }

}
