package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.neoevolution.mvc.service.AbstractInnovationEntityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class InnovationEntityController
        <T extends AbstractInnovationEntity, S extends AbstractInnovationEntityService<T, ?>>
        extends AbstractController<T, S> {

    protected InnovationEntityController(S service) {
        super(service);
    }


    @RequestMapping("/innovation/{innovation}")
    public List<T> findByInnovation(@PathVariable Long innovation) {
        return service.findByInnovation(innovation);
    }

}
