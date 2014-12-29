package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.service.NNConfigurationService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
public abstract class NNConfigurationController<T extends NNConfiguration, S extends NNConfigurationService<T, ?>>
        extends AbstractController<T, S> {

    protected NNConfigurationController(S service) {
        super(service);
    }

}
