package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.AbstractFitnessEntity;
import org.neoevolution.mvc.repository.FitnessEntityRepository;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 28 2014
 */
public abstract class AbstractFitnessEntityService
        <T extends AbstractFitnessEntity, R extends FitnessEntityRepository<T>>
        extends AbstractInnovationEntityService<T, R> {

    protected AbstractFitnessEntityService(R repository) {
        super(repository);
    }


    public List<T> findByFitness(Double fitness) {
        return repository.findByFitness(fitness);
    }

    public List<T> findByGeneration(Long generation) {
        return repository.findByGeneration(generation);
    }

}
