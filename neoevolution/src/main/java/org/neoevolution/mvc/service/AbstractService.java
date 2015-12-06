package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.AbstractEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractService<T extends AbstractEntity, R extends GraphRepository<T>> {

    protected int CAPACITY = 100;

    protected R repository;


    protected AbstractService(R repository) {
        this.repository = repository;
    }


    public long count() {
        return repository.count();
    }

    public boolean exists(Long id) {
        return repository.exists(id);
    }


    // FIND //

    @Transactional(readOnly = true)
    public T find(Long id) {
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public T find(T entity) {
        return (entity != null ? repository.findOne(entity.getId()) : null);
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


    // CREATE //

    @Transactional
    public final T create(T entity) {
        return create(entity, false);
    }

    @Transactional
    public T create(T entity, boolean updateReference)
    {
        T e = null;

        if (entity != null) {
            entity.setId(null);
            beforeCreate(entity, updateReference);
            e = repository.save(entity, 0);
            afterCreate(entity, updateReference);
        }
        return e;
    }

    @Transactional
    public final List<T> create(Iterable<T> entities) {
        return create(entities, false);
    }

    @Transactional
    public List<T> create(Iterable<T> entities, boolean updateReference)
    {
        List<T> es = new ArrayList<>(CAPACITY);

        if (entities != null) {
            for (T entity : entities) {
                es.add(create(entity, updateReference));
            }
        }
        return es;
    }


    // UPDATE //

    @Transactional
    public T update(T entity)
    {
        T e = null;

        if (entity != null && entity.getId() != null)
        {
            T dbEntity = find(entity.getId());

            if (dbEntity != null) {
                beforeUpdate(entity, dbEntity);
                e = repository.save(entity, 0);
                afterUpdate(entity, dbEntity);
            }
        }
        return e;
    }

    @Transactional
    public List<T> update(Iterable<T> entities)
    {
        List<T> es = new ArrayList<>(CAPACITY);

        if (entities != null) {
            for (T entity : entities) {
                es.add(update(entity));
            }
        }
        return es;
    }


    // DELETE //

    @Transactional
    public void delete(Long id) {
        delete(find(id));
    }

    @Transactional
    public void delete(T entity)
    {
        if (entity != null) {
            beforeDelete(entity);
            repository.delete(entity);
            afterDelete(entity);
        }
    }

    @Transactional
    public void delete(Iterable<T> entities)
    {
        if (entities != null) {
            for (T entity : entities) {
                delete(entity);
            }
        }
    }

    @Transactional
    public void deleteAll() {
        delete(findAll());
    }


    // BEFORE AND AFTER //

    protected final void beforeCreate(T entity) {
        beforeCreate(entity, false);
    }
    protected final void afterCreate(T entity) {
        afterCreate(entity, false);
    }

    protected void beforeCreate(T entity, boolean updateReference) { }
    protected void afterCreate(T entity, boolean updateReference) {
        repository.save(entity, 1);
    }

    protected void beforeUpdate(T entity, T dbEntity) { }
    protected void afterUpdate(T entity, T dbEntity) { }

    protected void beforeDelete(T entity) { }
    protected void afterDelete(T entity) { }

}
