package org.neoevolution.sample.soundfilter.mvc.repository;

import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.sample.soundfilter.mvc.model.SFEvolution;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface SFEvolutionRepository extends EvolutionRepository<SFEvolution> {

    @Override
    SFEvolution findByConfigurationId(Long id);

}
