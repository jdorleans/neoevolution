package org.neoevolution.mvc;

import org.neoevolution.core.GeneticAlgorithm;
import org.neoevolution.mvc.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@Service
public class EvolutionService {

    @Autowired
    private GeneticAlgorithm geneticAlgorithm;

    @Autowired
    private PopulationService populationService;


    public void evolve() {
        geneticAlgorithm.evolve();
//        populationService.save(algorithm.getPopulation());
    }

}
