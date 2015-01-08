package org.neoevolution.mvc.controller;

import org.neoevolution.core.operator.activation.DataSet;
import org.neoevolution.core.operator.activation.EntityDataSet;
import org.neoevolution.mvc.service.GenotypeActivationService;
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
public class GenotypeErrorController {

    @Autowired
    private GenotypeActivationService activationService;


    @RequestMapping(value = "/{id}/error", method = RequestMethod.POST)
    public DataSet activate(@PathVariable Long id, @RequestBody DataSet inputSet)
            throws ExecutionException, InterruptedException {
        return activationService.activate(id, inputSet);
    }

    @RequestMapping(value = "/error/batch", method = RequestMethod.POST)
    public List<EntityDataSet> activate(@RequestBody List<EntityDataSet> entityInputSet) {
        return activationService.activate(entityInputSet);
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public List<EntityDataSet> activateAll(@RequestBody DataSet inputSet) {
        return activationService.activateAll(inputSet);
    }

}
