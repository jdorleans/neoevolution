package org.neoevolution.mvc.repository;

import org.neoevolution.core.GAConfiguration;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 01 2014
 */
public interface GAConfigurationRepository extends GraphRepository<GAConfiguration> {

}
