package org.neoevolution.xor.mvc.repository;

import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.xor.mvc.model.XOREvolution;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Dec 23 2014
 */
public interface XOREvolutionRepository extends EvolutionRepository<XOREvolution> {

    @Override
    XOREvolution findByConfigurationId(Long id);

}
