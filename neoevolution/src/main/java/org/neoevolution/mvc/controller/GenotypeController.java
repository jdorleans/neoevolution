package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/genotype")
public class GenotypeController extends FitnessEntityController<Genotype, GenotypeService> {

    @Autowired
    protected GenotypeController(GenotypeService service) {
        super(service);
    }


    @RequestMapping(value = "/{id}/neuron/{neuronId}/add")
    public Genotype addNeuron(@PathVariable Long id, @PathVariable Long neuronId,
                              @RequestParam(required = false) boolean project) {
        return projection(service.addNeuron(id, neuronId), project);
    }

}
