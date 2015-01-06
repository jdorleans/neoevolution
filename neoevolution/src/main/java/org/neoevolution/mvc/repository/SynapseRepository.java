package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Synapse;

import java.util.List;

public interface SynapseRepository extends InnovationEntityRepository<Synapse> {

    @Override
    List<Synapse> findByInnovation(Long innovation);

}