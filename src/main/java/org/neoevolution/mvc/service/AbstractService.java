package org.neoevolution.mvc.service;

import org.neoevolution.mvc.AbstractEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
public abstract class AbstractService<T extends AbstractEntity, R extends GraphRepository<T>> {

    protected R repository;


    protected AbstractService(R repository) {
        this.repository = repository;
    }


    @Transactional
    public void save(T entity)
    {
        System.out.println("Saving: "+ entity);
        beforeSave(entity);
        repository.save(entity);
        afterSave(entity);
    }

    @Transactional
    public void save(Collection<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
    }

    protected void beforeSave(T entity) { }

    protected void afterSave(T entity) { }

}
