package org.neoevolution.mvc;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.GeneticAlgorithm;
import org.neoevolution.factory.GeneticAlgorithmFactory;
import org.neoevolution.mvc.repository.GAConfigurationRepository;
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
    private PopulationService populationService;

    @Autowired
    private GAConfigurationRepository gaConfigurationRepository;


    public void evolve(GAConfiguration configuration)
    {
        gaConfigurationRepository.save(configuration);
        GeneticAlgorithmFactory factory = new GeneticAlgorithmFactory();
        factory.configure(configuration);
        GeneticAlgorithm geneticAlgorithm = factory.create();
        geneticAlgorithm.evolve();
    }

}
