package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.innovation.AbstractInnovation;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
@NoRepositoryBean
public interface InnovationRepository<T extends AbstractInnovation> extends GraphRepository<T> {

    T findByConfigId(Long configId);

}
