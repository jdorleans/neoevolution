package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.Species;
import org.neoevolution.mvc.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
@RestController
@RequestMapping("/species")
public class SpeciesController extends FitnessEntityController<Species, SpeciesService> {

    @Autowired
    protected SpeciesController(SpeciesService service) {
        super(service);
    }

}
