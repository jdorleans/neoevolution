package org.neoevolution.core.algorithm;

import org.neoevolution.mvc.model.Population;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 18 2014
 */
public interface NNAlgorithm {

    void evolve();

    Population getPopulation();

}
