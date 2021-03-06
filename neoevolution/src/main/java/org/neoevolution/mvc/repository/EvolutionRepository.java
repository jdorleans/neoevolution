package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Evolution;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
* @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
* @since 1.0
*/
@NoRepositoryBean
public interface EvolutionRepository<T extends Evolution> extends GraphRepository<T> {

    T findByConfigurationId(Long configurationId);

}
