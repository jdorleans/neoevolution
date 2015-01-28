package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.mvc.service.NEConfigurationService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class NEConfigurationController<T extends NEConfiguration, S extends NEConfigurationService<T, ?>>
        extends NNConfigurationController<T, S> {

    protected NEConfigurationController(S service) {
        super(service);
    }
    
}
