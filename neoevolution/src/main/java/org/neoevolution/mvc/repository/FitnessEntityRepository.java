package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.AbstractFitnessEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NoRepositoryBean
public interface FitnessEntityRepository<T extends AbstractFitnessEntity> extends InnovationEntityRepository<T> {

    List<T> findByFitness(Double fitness);

    List<T> findByGeneration(Long generation);

}
