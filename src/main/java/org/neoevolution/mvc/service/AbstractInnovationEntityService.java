package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.neoevolution.mvc.repository.InnovationEntityRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 28 2014
 */
public abstract class AbstractInnovationEntityService
        <T extends AbstractInnovationEntity, R extends InnovationEntityRepository<T>>
        extends AbstractService<T, R> {

    protected AbstractInnovationEntityService(R repository) {
        super(repository);
    }

}
