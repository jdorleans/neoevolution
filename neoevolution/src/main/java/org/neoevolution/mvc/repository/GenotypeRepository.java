package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Genotype;

import java.util.List;

public interface GenotypeRepository extends FitnessEntityRepository<Genotype> {

    @Override
    List<Genotype> findByInnovation(Long innovation);

    @Override
    List<Genotype> findByFitness(Double fitness);

    @Override
    List<Genotype> findByGeneration(Long generation);

}