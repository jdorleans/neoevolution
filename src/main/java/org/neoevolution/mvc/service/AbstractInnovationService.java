package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.innovation.AbstractInnovation;
import org.neoevolution.mvc.repository.InnovationRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 05 2014
 */
public abstract class AbstractInnovationService<T extends AbstractInnovation, R extends InnovationRepository<T>>
        extends AbstractService<T, R> {

    protected AbstractInnovationService(R repository) {
        super(repository);
    }

}
