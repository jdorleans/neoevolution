package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<T> find(List<Long> ids)
    {
        List<T> entities = new ArrayList<>(ids.size());

        for (Long id : ids)
        {
            T entity = repository.findOne(id);

            if (entity != null) {
                entities.add(entity);
            }
        }
        return entities;
    }

    public T find(T entity) {
        return (entity != null ? repository.findOne(entity.getId()) : null);
    }

    public T findByProperty(String property, Object value) {
        return repository.findBySchemaPropertyValue(property, value);
    }

    @Transactional(readOnly = true)
    public List<T> findAll()
    {
        List<T> list = new ArrayList<>();

        for (T t : repository.findAll()) {
            list.add(t);
        }
        return list;
    }


    public final void save(T entity) {
        save(entity, false);
    }

    @Transactional
    public void save(T entity, boolean updateReference) {
        beforeSave(entity, updateReference);
        repository.save(entity);
        afterSave(entity, updateReference);
    }

    public final void save(Iterable<T> entities) {
        save(entities, false);
    }

    @Transactional
    public void save(Iterable<T> entities, boolean updateReference) {
        for (T entity : entities) {
            save(entity, updateReference);
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

    protected final void beforeSave(T entity) {
        beforeSave(entity, false);
    }
    protected final void afterSave(T entity) {
        afterSave(entity, false);
    }

    protected void beforeSave(T entity, boolean updateReference) { }
    protected void afterSave(T entity, boolean updateReference) { }

    protected void beforeDelete(T entity) { }
    protected void afterDelete(T entity) { }

}
