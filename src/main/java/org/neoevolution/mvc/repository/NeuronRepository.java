package org.neoevolution.mvc.repository;

import org.neoevolution.core.model.Neuron;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface NeuronRepository extends GraphRepository<Neuron> {

}