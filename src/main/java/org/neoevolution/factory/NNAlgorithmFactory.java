package org.neoevolution.factory;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.core.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public interface NNAlgorithmFactory<T extends NNAlgorithm, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
