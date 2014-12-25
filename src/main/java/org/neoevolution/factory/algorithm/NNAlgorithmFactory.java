package org.neoevolution.factory.algorithm;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public interface NNAlgorithmFactory<T extends NNAlgorithm, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
