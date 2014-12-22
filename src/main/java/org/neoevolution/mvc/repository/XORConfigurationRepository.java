package org.neoevolution.mvc.repository;

import org.neoevolution.core.configuration.XORConfiguration;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 01 2014
 */
public interface XORConfigurationRepository extends GraphRepository<XORConfiguration> {

}
