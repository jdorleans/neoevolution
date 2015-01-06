package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Population;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
public interface PopulationRepository extends FitnessEntityRepository<Population> {

    @Override
    List<Population> findByInnovation(Long innovation);

    @Override
    List<Population> findByFitness(Double fitness);

    @Override
    List<Population> findByGeneration(Long generation);

}
