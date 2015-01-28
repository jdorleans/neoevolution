package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.AbstractFitnessEntity;
import org.neoevolution.mvc.service.AbstractFitnessEntityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class FitnessEntityController
        <T extends AbstractFitnessEntity, S extends AbstractFitnessEntityService<T, ?>>
        extends InnovationEntityController<T, S> {

    protected FitnessEntityController(S service) {
        super(service);
    }

    @RequestMapping("/fitness/{fitness}")
    public List<T> findByFitness(@PathVariable Double fitness) {
        return service.findByFitness(fitness);
    }

    @RequestMapping("/generation/{generation}")
    public List<T> findByGeneration(@PathVariable Long generation) {
        return service.findByGeneration(generation);
    }

}
