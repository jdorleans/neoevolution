package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Synapse;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface SynapseRepository extends InnovationEntityRepository<Synapse> {

    @Override
    List<Synapse> findByInnovation(Long innovation);

}