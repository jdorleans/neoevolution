package org.neoevolution.xor.mvc.repository;

import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.xor.mvc.model.XOREvolution;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface XOREvolutionRepository extends EvolutionRepository<XOREvolution> {

    @Override
    XOREvolution findByConfigurationId(Long id);

}
