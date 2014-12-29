package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.AbstractFitnessEntity;
import org.neoevolution.mvc.service.AbstractService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
public abstract class FitnessEntityController<T extends AbstractFitnessEntity, S extends AbstractService<T, ?>>
        extends InnovationEntityController<T, S> {

    protected FitnessEntityController(S service) {
        super(service);
    }


}
