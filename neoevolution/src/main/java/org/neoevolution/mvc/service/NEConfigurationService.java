package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.mvc.repository.NEConfigurationRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class NEConfigurationService<T extends NEConfiguration, R extends NEConfigurationRepository<T>>
        extends NNConfigurationService<T, R> {

    protected NEConfigurationService(R repository) {
        super(repository);
    }

}
