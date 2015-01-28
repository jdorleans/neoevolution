package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.mvc.service.SynapseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/synapse")
public class SynapseController extends InnovationEntityController<Synapse, SynapseService> {

    @Autowired
    protected SynapseController(SynapseService service) {
        super(service);
    }

}
