package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NoRepositoryBean
public interface NNConfigurationRepository<T extends NNConfiguration> extends GraphRepository<T> {

}
