package org.neoevolution.mvc.service;

import org.neoevolution.core.algorithm.AbstractNEAlgorithm;
import org.neoevolution.factory.algorithm.AbstractNEAlgorithmFactory;
import org.neoevolution.mvc.model.Evolution;
import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.mvc.repository.EvolutionRepository;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since 1.0
 */
public abstract class NEEvolutionService<T extends Evolution<C>, C extends NEConfiguration,
        R extends EvolutionRepository<T>, F extends AbstractNEAlgorithmFactory<? extends AbstractNEAlgorithm, ?, ?, C>>
        extends EvolutionService<T, C, R, F> {

    protected NEEvolutionService(R repository, NEConfigurationService<C, ?> configurationService) {
        super(repository, configurationService);
    }

}
