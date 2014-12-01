package org.neoevolution.mvc.repository;

import org.neoevolution.core.innovation.SynapseInnovation;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface SynapseInnovationRepository extends GraphRepository<SynapseInnovation> {

    SynapseInnovation findByConfigId(Long id);

}
