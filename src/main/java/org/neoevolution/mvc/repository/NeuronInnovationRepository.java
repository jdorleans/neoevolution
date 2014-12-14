package org.neoevolution.mvc.repository;

import org.neoevolution.core.innovation.NeuronInnovation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface NeuronInnovationRepository extends InnovationRepository<NeuronInnovation> {

    @Override
    NeuronInnovation findByConfigId(Long configId);

}
