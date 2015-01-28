package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/population")
public class PopulationController extends FitnessEntityController<Population, PopulationService> {

    @Autowired
    protected PopulationController(PopulationService service) {
        super(service);
    }

}
