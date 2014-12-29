package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.neoevolution.mvc.service.AbstractService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
public abstract class InnovationEntityController<T extends AbstractInnovationEntity, S extends AbstractService<T, ?>>
        extends AbstractController<T, S> {

    protected InnovationEntityController(S service) {
        super(service);
    }

}
