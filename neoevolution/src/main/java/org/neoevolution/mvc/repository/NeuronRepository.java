package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Neuron;

import java.util.List;

public interface NeuronRepository extends InnovationEntityRepository<Neuron> {

    @Override
    List<Neuron> findByInnovation(Long innovation);

}