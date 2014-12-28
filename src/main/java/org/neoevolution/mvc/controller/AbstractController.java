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


    @RequestMapping(value = "/count")
    public long count() {
        return service.count();
    }

    @RequestMapping(value = "/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return service.exists(id);
    }


    @RequestMapping(value = "/find/{id}")
    public T find(@PathVariable Long id) {
        return service.find(id);
    }

    @RequestMapping(value = "/find/all")
    public Iterable<T> findAll() {
        return service.findAll();
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public T save(@RequestBody T entity) {
        service.save(entity, true);
        return entity;
    }

    @RequestMapping(value = "/save/list", method = RequestMethod.POST)
    public List<T> save(@RequestBody List<T> entities) {
        service.save(entities, true);
        return entities;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody T entity) {
        service.delete(entity);
    }

    @RequestMapping(value = "/delete/list", method = RequestMethod.DELETE)
    public void delete(@RequestBody List<T> entities) {
        service.delete(entities);
    }

    @RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
    public void deleteAll() {
        service.deleteAll();
    }

}
