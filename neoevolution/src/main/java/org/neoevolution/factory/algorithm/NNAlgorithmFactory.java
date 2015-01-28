package org.neoevolution.factory.algorithm;

import org.neoevolution.core.algorithm.NNAlgorithm;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface NNAlgorithmFactory<T extends NNAlgorithm, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
