package org.neoevolution.mvc.service;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.factory.algorithm.NNAlgorithmFactory;
import org.neoevolution.mvc.model.Evolution;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.repository.EvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since 1.0
 */
public abstract class EvolutionService<T extends Evolution<C>, C extends NNConfiguration,
        R extends EvolutionRepository<T>, F extends NNAlgorithmFactory<? extends NNAlgorithm, C>>
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

    public List<T> evolve(List<C> configurations, Boolean create)
    {
        List<T> evolutions = Collections.synchronizedList(new ArrayList<>(configurations.size()));

        configurations.parallelStream().forEach(c -> {
            evolutions.add(evolve(c, create));
        });
        return evolutions;
    }

    public T evolve(C configuration, Boolean create)
    {
        T evolution = createEvolution(configuration);
        create(evolution, create);

        NNAlgorithm algorithm = createAlgorithm(configuration);
        algorithm.evolve();

        evolution.setFinished(true);
        evolution.setPopulation(algorithm.getPopulation());
        return create(evolution, create);
    }

    private NNAlgorithm createAlgorithm(C configuration) {
        F factory = createFactory();
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

    protected abstract F createFactory();

}
