package org.neoevolution.sample.autopilot.mvc.controller;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeService;
import org.neoevolution.sample.autopilot.AutoPilotNeoEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 26 2015
 */
@Controller
@RequestMapping("/autopilot/{id}/play")
public class AutoPilotApplicationController {

    @Autowired
    private GenotypeService genotypeService;

    @RequestMapping
    public void play(@PathVariable Long id, @RequestParam(required = false) Integer runs)
    {
        if (runs == null) {
            runs = 1;
        }
        Genotype genotype = genotypeService.find(id);

        for (int i = 0; i < runs; i++)
        {
            genotype.setFitness(0d);
            AutoPilotNeoEvolution.application.run(genotype);

            while (AutoPilotNeoEvolution.application.isRunning()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
