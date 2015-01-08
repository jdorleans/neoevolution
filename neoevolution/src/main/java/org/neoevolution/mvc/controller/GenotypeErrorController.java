package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.dataset.EntityDataSet;
import org.neoevolution.mvc.dataset.ErrorDataSet;
import org.neoevolution.mvc.service.GenotypeErrorService;
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
    private GenotypeErrorService service;


    @RequestMapping(value = "/{id}/error", method = RequestMethod.POST)
    public EntityDataSet activate(@PathVariable Long id, @RequestBody ErrorDataSet dataSet)
            throws ExecutionException, InterruptedException {
        return service.calculateEntity(id, dataSet);
    }

    @RequestMapping(value = "/error/batch", method = RequestMethod.POST)
    public List<EntityDataSet> activate(@RequestBody List<ErrorDataSet> dataSets) {
        return service.calculate(dataSets);
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public List<EntityDataSet> activateAll(@RequestBody ErrorDataSet dataSet) {
        return service.calculateAll(dataSet);
    }

}
