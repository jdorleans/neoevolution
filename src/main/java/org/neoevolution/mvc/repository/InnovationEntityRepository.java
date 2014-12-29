package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.AbstractInnovationEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 28 2014
 */
@NoRepositoryBean
public interface InnovationEntityRepository<T extends AbstractInnovationEntity> extends GraphRepository<T> {

    List<T> findByInnovation(Long innovation);

}
