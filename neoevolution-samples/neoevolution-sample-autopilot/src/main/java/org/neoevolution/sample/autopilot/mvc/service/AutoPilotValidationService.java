package org.neoevolution.sample.autopilot.mvc.service;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeService;
import org.neoevolution.sample.autopilot.AutoPilotNeoEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class AutoPilotValidationService {

    @Autowired
    private GenotypeService genotypeService;


    public void validate(Long id, Integer runs, Integer maxFitness) {
        validate(genotypeService.find(id), runs, maxFitness);
    }


    private void validate(Genotype genotype, Integer runs, Integer maxFitness)
    {
        runs = fixRuns(runs);
        maxFitness = fixMaxFitness(maxFitness);
        AutoPilotNeoEvolution.application.setMaxScores((double) maxFitness);

        for (int i = 0; i < runs; i++) {
            genotype.setFitness(0d);
            AutoPilotNeoEvolution.application.run(genotype);
            runWait();
        }

    }

    private void runWait()
    {
        while (AutoPilotNeoEvolution.application.isRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer fixRuns(Integer runs)
    {
        if (runs == null) {
            runs = 1;
        }
        return runs;
    }

    private Integer fixMaxFitness(Integer maxFitness)
    {
        if (maxFitness == null) {
            maxFitness = 50;
        }
        return maxFitness;
    }


}
