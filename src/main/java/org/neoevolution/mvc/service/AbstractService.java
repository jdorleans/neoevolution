package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
public abstract class AbstractService<T extends AbstractEntity, R extends GraphRepository<T>> {

    protected R repository;

    @Autowired
    protected Neo4jTemplate template;

    protected AbstractService(R repository) {
        this.repository = repository;
    }


    public long count() {
        return repository.count();
    }

    public boolean exists(Long id) {
        return repository.exists(id);
    }

    public T find(Long id) {
        return repository.findOne(id);
    }

    // FIXME
    @Transactional
    public Iterable<T> findAll() {
        return repository.findAll();
    }


    @Transactional
    public void save(T entity) {
        beforeSave(entity);
        repository.save(entity);
        afterSave(entity);
    }

    @Transactional
    public void save(Iterable<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
    }


    @Transactional
    public void delete(Long id) {
        delete(find(id));
    }

    @Transactional
    public void delete(T entity) {
        beforeDelete(entity);
        repository.delete(entity);
        afterDelete(entity);
    }

    @Transactional
    public void delete(Iterable<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Transactional
    public void deleteAll() {
        delete(findAll());
    }

    protected void beforeSave(T entity) { }
    protected void afterSave(T entity) { }

    protected void beforeDelete(T entity) { }
    protected void afterDelete(T entity) { }

}
