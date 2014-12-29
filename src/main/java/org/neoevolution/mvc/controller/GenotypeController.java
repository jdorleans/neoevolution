package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
@RestController
@RequestMapping("/genotype")
public class GenotypeController extends FitnessEntityController<Genotype, GenotypeService> {

    @Autowired
    protected GenotypeController(GenotypeService service) {
        super(service);
    }

}
