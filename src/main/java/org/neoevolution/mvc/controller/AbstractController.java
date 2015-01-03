package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.AbstractEntity;
import org.neoevolution.mvc.service.AbstractService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 25 2014
 */
public abstract class AbstractController<T extends AbstractEntity, S extends AbstractService<T, ?>> {

    protected S service;


    protected AbstractController(S service) {
        this.service = service;
    }


    @RequestMapping("/count")
    public long count() {
        return service.count();
    }

    @RequestMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return service.exists(id);
    }


    // FIND //

    @RequestMapping("/{id}")
    public T find(@PathVariable Long id) {
        return service.find(id);
    }

    @RequestMapping("/batch")
    public List<T> find(@RequestBody List<Long> ids) {
        return service.find(ids);
    }

    @RequestMapping
    public Iterable<T> findAll() {
        return service.findAll();
    }


    // CREATE //

    @RequestMapping(method = RequestMethod.POST)
    public T create(@RequestBody T entity) {
        return service.create(entity, true);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public List<T> create(@RequestBody List<T> entities) {
        return service.create(entities, true);
    }


    // UPDATE //

    @RequestMapping(method = RequestMethod.PUT)
    public T update(@RequestBody T entity) {
        return service.update(entity);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.PUT)
    public List<T> update(@RequestBody List<T> entities) {
        return service.update(entities);
    }


    // DELETE //

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.DELETE)
    public void delete(@RequestBody List<T> entities) {
        service.delete(entities);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        service.deleteAll();
    }

}
