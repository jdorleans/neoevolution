package org.neoevolution.mvc.service;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.factory.algorithm.NNAlgorithmFactory;
import org.neoevolution.mvc.model.Evolution;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.repository.EvolutionRepository;
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

    protected NNConfigurationService<C, ?> configurationService;


    protected EvolutionService(R repository, NNConfigurationService<C, ?> configurationService) {
        super(repository);
        this.configurationService = configurationService;
    }


    public T evolve(C configuration) {
        return evolve(configuration, true);
    }

    public T evolve(C configuration, Boolean save)
    {
        T evolution = createEvolution(configuration);
        save(evolution, save);

        NNAlgorithm algorithm = createAlgorithm(configuration);
        algorithm.evolve();

        evolution.setFinished(true);
        evolution.setPopulation(algorithm.getPopulation());
        save(evolution, save);
        return evolution;
    }

    private NNAlgorithm createAlgorithm(C configuration) {
        NNAlgorithmFactory<NNAlgorithm, C> factory = createFactory();
        factory.configure(configuration);
        return factory.create();
    }

    private T createEvolution(C configuration) {
        T evolution = create();
        evolution.setConfiguration(configuration);
        return evolution;
    }


    protected void save(T evolution, Boolean save) {
        if (save == null || save) {
            super.save(evolution);
        }
    }

    @Override
    protected void beforeSave(T entity, boolean updateReference)
    {
        Population population = entity.getPopulation();

        if (population != null) {
            populationService.save(population, updateReference);
        }
        configurationService.save(entity.getConfiguration(), updateReference);
    }

    protected abstract T create();

    protected abstract <F extends NNAlgorithmFactory> F createFactory();

}
