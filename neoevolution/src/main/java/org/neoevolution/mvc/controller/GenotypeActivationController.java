package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.dataset.ListDataSet;
import org.neoevolution.mvc.service.GenotypeActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/genotype")
public class GenotypeActivationController {

    @Autowired
    private GenotypeActivationService service;


    @RequestMapping(value = "/{id}/activate", method = RequestMethod.POST)
    public ListDataSet activate(@PathVariable Long id, @RequestBody ListDataSet inputSet)
            throws ExecutionException, InterruptedException {
        return service.activate(id, inputSet);
    }

    @RequestMapping(value = "/activate/batch", method = RequestMethod.POST)
    public List<ListDataSet> activate(@RequestBody List<ListDataSet> entityInputSet) {
        return service.activate(entityInputSet);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public List<ListDataSet> activateAll(@RequestBody ListDataSet inputSet) {
        return service.activateAll(inputSet);
    }

}
