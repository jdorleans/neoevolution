package org.neoevolution.mvc.controller;

import org.neoevolution.core.operator.activation.EntitySampleData;
import org.neoevolution.core.operator.activation.SampleData;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeActivationService;
import org.neoevolution.mvc.service.GenotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
@RestController
@RequestMapping("/genotype")
public class GenotypeController extends FitnessEntityController<Genotype, GenotypeService> {

    @Autowired
    private GenotypeActivationService activationService;


    @Autowired
    protected GenotypeController(GenotypeService service) {
        super(service);
    }


    @RequestMapping(value = "/{id}/activate", method = RequestMethod.POST)
    public EntitySampleData activate(@PathVariable Long id, @RequestBody SampleData sample)
            throws ExecutionException, InterruptedException {
        return activationService.activate(id, sample);
    }

    @RequestMapping(value = "/activate/batch", method = RequestMethod.POST)
    public List<EntitySampleData> activate(@RequestBody List<EntitySampleData> samples) {
        return activationService.activate(samples);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public List<EntitySampleData> activateAll(@RequestBody SampleData sample) {
        return activationService.activateAll(sample);
    }


    @RequestMapping(value = "/{id}/neuron/{neuronId}/add")
    public Genotype addNeuron(@PathVariable Long id, @PathVariable Long neuronId,
                              @RequestParam(required = false) boolean project) {
        return projection(service.addNeuron(id, neuronId), project);
    }

}
