package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.mvc.service.NEConfigurationService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
public abstract class NEConfigurationController<T extends NEConfiguration, S extends NEConfigurationService<T, ?>>
        extends AbstractController<T, S> {

    protected NEConfigurationController(S service) {
        super(service);
    }
    
}
