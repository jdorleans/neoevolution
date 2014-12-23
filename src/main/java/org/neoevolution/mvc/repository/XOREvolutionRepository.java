package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.XOREvolution;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Dec 23 2014
 */
public interface XOREvolutionRepository extends EvolutionRepository<XOREvolution> {

    @Override
    XOREvolution findByConfigurationId(Long id);

}
