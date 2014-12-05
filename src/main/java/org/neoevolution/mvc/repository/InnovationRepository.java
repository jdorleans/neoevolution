package org.neoevolution.mvc.repository;

import org.neoevolution.core.innovation.AbstractInnovation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface InnovationRepository<T extends AbstractInnovation> {

    T findByConfigId(Long configId);

}
