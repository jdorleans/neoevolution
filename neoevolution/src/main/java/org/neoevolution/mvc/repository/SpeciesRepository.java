package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Species;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface SpeciesRepository extends FitnessEntityRepository<Species> {

    @Override
    List<Species> findByInnovation(Long innovation);

    @Override
    List<Species> findByFitness(Double fitness);

    @Override
    List<Species> findByGeneration(Long generation);

}
