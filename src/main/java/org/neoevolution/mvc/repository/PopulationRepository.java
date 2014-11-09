package org.neoevolution.mvc.repository;

import org.neoevolution.core.Population;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
public interface PopulationRepository extends GraphRepository<Population> {

}
