package org.neoevolution.mvc.service;

import org.neoevolution.core.innovation.AbstractInnovation;
import org.neoevolution.mvc.repository.InnovationRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 05 2014
 */
public abstract class AbstractInnovationService<T extends AbstractInnovation, R extends InnovationRepository<T>>
        extends AbstractService<T, R> {

    protected Map<Long, T> innovations;


    protected AbstractInnovationService(R repository) {
        super(repository);
        this.repository = repository;
        innovations = new HashMap<>();
    }


    public abstract T create(Long configId);

    public T findOrCreate(Long configId)
    {
        T innovation = innovations.get(configId);

        if (innovation == null)
        {
            innovation = repository.findByConfigId(configId);

            if (innovation == null) {
                innovation = create(configId);
            }
            innovations.put(configId, innovation);
        }
        return innovation;
    }

}
