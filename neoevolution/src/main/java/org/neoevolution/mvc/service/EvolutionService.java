package org.neoevolution.mvc.service;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.factory.algorithm.NNAlgorithmFactory;
import org.neoevolution.mvc.model.Evolution;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.repository.EvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

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


    @Async
    public Future<T> evolve(C configuration) {
        return evolve(configuration, true);
    }

    @Async
    public Future<T> evolve(C configuration, Boolean create)
    {
        T evolution = createEvolution(configuration);
        create(evolution, create);

        NNAlgorithm algorithm = createAlgorithm(configuration);
        algorithm.evolve();

        evolution.setFinished(true);
        evolution.setPopulation(algorithm.getPopulation());
        return new AsyncResult<>(create(evolution, create));
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


    protected T create(T evolution, Boolean create) {
        if (create == null || create) {
            return super.create(evolution);
        }
        return evolution;
    }

    @Override
    protected void beforeCreate(T entity, boolean updateReference) {
        populationService.create(entity.getPopulation(), updateReference);
        configurationService.create(entity.getConfiguration(), updateReference);
    }

    @Override
    protected void beforeUpdate(T entity, T dbEntity) {
        entity.setPopulation(dbEntity.getPopulation());
        entity.setConfiguration(dbEntity.getConfiguration());
    }

    protected abstract T create();

    protected abstract <F extends NNAlgorithmFactory> F createFactory();

}
