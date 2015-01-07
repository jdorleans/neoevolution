package org.neoevolution.sample.robot.mvc.service;

import org.neoevolution.mvc.service.NEEvolutionService;
import org.neoevolution.sample.robot.factory.RobotAlgorithmFactory;
import org.neoevolution.sample.robot.mvc.model.RobotConfiguration;
import org.neoevolution.sample.robot.mvc.model.RobotEvolution;
import org.neoevolution.sample.robot.mvc.repository.RobotEvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class RobotEvolutionService extends NEEvolutionService<RobotEvolution, RobotConfiguration, RobotEvolutionRepository> {

    @Autowired
    protected RobotEvolutionService(RobotEvolutionRepository repository, RobotConfigurationService configurationService) {
        super(repository, configurationService);
    }

    @Override
    protected RobotEvolution create() {
        return new RobotEvolution();
    }

    @Override
    protected RobotAlgorithmFactory createFactory() {
        return new RobotAlgorithmFactory();
    }

}
