package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.innovation.SynapseInnovation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface SynapseInnovationRepository extends InnovationRepository<SynapseInnovation> {

    @Override
    SynapseInnovation findByConfigId(Long configId);

}
