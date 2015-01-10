package org.neoevolution.sample.autopilot.mvc.repository;

import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotEvolution;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface AutoPilotEvolutionRepository extends EvolutionRepository<AutoPilotEvolution> {

    @Override
    AutoPilotEvolution findByConfigurationId(Long id);

}
