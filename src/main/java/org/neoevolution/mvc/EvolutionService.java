package org.neoevolution.mvc;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.model.Population;
import org.neoevolution.factory.NNAlgorithmFactory;
import org.neoevolution.mvc.repository.EvolutionRepository;
import org.neoevolution.mvc.service.AbstractService;
import org.neoevolution.mvc.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Dec 23 2014
 */
public abstract class EvolutionService
        <T extends Evolution<C>, C extends NNConfiguration, R extends EvolutionRepository<T>>
        extends AbstractService<T, R> {

    @Autowired
    protected PopulationService populationService;


    protected EvolutionService(R repository) {
        super(repository);
    }


    public void evolve(C configuration)
    {
        NNAlgorithm algorithm = createAlgorithm(configuration);
        T evolution = initEvolution(configuration, algorithm.getPopulation());
        repository.save(evolution);

        algorithm.evolve();
        evolution.setFinished(true);
        repository.save(evolution);
    }

    private T initEvolution(C configuration, Population population) {
        T evolution = create();
        evolution.setConfiguration(configuration);
        evolution.setPopulation(population);
        return evolution;
    }

    // FIXME - EITHER SAVE CONFIG OR SETUP NEURON AND SYNAPSES INNOVATIONS
    private NNAlgorithm createAlgorithm(C configuration) {
        NNAlgorithmFactory<NNAlgorithm, C> factory = createFactory();
        factory.configure(configuration);
        return factory.create();
    }

    @Override
    protected void beforeSave(T entity) {
        populationService.save(entity.getPopulation());
    }

    protected abstract T create();

    protected abstract <F extends NNAlgorithmFactory> F createFactory();


}
