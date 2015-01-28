package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.neoevolution.mvc.repository.InnovationEntityRepository;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractInnovationEntityService
        <T extends AbstractInnovationEntity, R extends InnovationEntityRepository<T>>
        extends AbstractService<T, R> {

    protected AbstractInnovationEntityService(R repository) {
        super(repository);
    }

    public List<T> findByInnovation(Long innovation) {
        return repository.findByInnovation(innovation);
    }

}
