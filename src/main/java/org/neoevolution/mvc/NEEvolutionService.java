package org.neoevolution.mvc;

import org.neoevolution.core.configuration.NEConfiguration;
import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.mvc.service.NEConfigurationService;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Dec 23 2014
 */
public abstract class NEEvolutionService
        <T extends Evolution<C>, C extends NEConfiguration, R extends EvolutionRepository<T>>
        extends NNEvolutionService<T, C, R> {

    protected NEEvolutionService(R repository, NEConfigurationService<C, ?> configurationService) {
        super(repository, configurationService);
    }

}
