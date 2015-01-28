package org.neoevolution.mvc.controller;

import org.neoevolution.mvc.dataset.EntityDataSet;
import org.neoevolution.mvc.dataset.FitnessDataSet;
import org.neoevolution.mvc.service.GenotypeFitnessService;
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
public class GenotypeFitnessController {

    @Autowired
    private GenotypeFitnessService service;


    @RequestMapping(value = "/{id}/fitness", method = RequestMethod.POST)
    public EntityDataSet activate(@PathVariable Long id, @RequestBody FitnessDataSet dataSet)
            throws ExecutionException, InterruptedException {
        return service.calculateEntity(id, dataSet);
    }

    @RequestMapping(value = "/fitness/batch", method = RequestMethod.POST)
    public List<EntityDataSet> activate(@RequestBody List<FitnessDataSet> dataSets) {
        return service.calculate(dataSets);
    }

    @RequestMapping(value = "/fitness", method = RequestMethod.POST)
    public List<EntityDataSet> activateAll(@RequestBody FitnessDataSet dataSet) {
        return service.calculateAll(dataSet);
    }

}
