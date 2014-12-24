package org.neoevolution.xor;

import org.neoevolution.mvc.repository.EvolutionRepository;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Dec 23 2014
 */
public interface XOREvolutionRepository extends EvolutionRepository<XOREvolution> {

    @Override
    XOREvolution findByConfigurationId(Long id);

}
