package org.neoevolution.mvc.repository;

import org.neoevolution.core.model.Genotype;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface GenotypeRepository extends GraphRepository<Genotype> {

}