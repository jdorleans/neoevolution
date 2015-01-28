package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.Neuron;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface NeuronRepository extends InnovationEntityRepository<Neuron> {

    @Override
    List<Neuron> findByInnovation(Long innovation);

}