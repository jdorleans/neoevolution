package org.neoevolution.mvc;

import org.neoevolution.core.algorithm.XORAlgorithm;
import org.neoevolution.core.configuration.XORConfiguration;
import org.neoevolution.factory.XORAlgorithmFactory;
import org.neoevolution.mvc.repository.XORConfigurationRepository;
import org.neoevolution.mvc.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@Service
public class XOREvolutionService {

    @Autowired
    private PopulationService populationService;

    @Autowired
    private XORConfigurationRepository configurationRepository;

    public void evolve(XORConfiguration configuration) {
//        Evolution evolution = new Evolution();
//        evolutionRepository.save(evolution);
        configurationRepository.save(configuration);
        XORAlgorithmFactory factory = new XORAlgorithmFactory();
        factory.configure(configuration);
        XORAlgorithm algorithm = factory.create();
        algorithm.evolve();
//        populationService.save(algorithm.getPopulation());
    }

}
