package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.service.NeuronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
@RestController
@RequestMapping("/neuron")
public class NeuronController extends InnovationEntityController<Neuron, NeuronService> {

    @Autowired
    protected NeuronController(NeuronService service) {
        super(service);
    }

}
