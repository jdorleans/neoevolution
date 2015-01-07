package org.neoevolution.sample.robot.mvc.repository;

import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.sample.robot.mvc.model.RobotEvolution;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface RobotEvolutionRepository extends EvolutionRepository<RobotEvolution> {

    @Override
    RobotEvolution findByConfigurationId(Long id);

}
