package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Evolution;
import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.mvc.repository.EvolutionRepository;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since 1.0
 */
public abstract class NEEvolutionService
        <T extends Evolution<C>, C extends NEConfiguration, R extends EvolutionRepository<T>>
        extends EvolutionService<T, C, R> {

    protected NEEvolutionService(R repository, NEConfigurationService<C, ?> configurationService) {
        super(repository, configurationService);
    }

}
