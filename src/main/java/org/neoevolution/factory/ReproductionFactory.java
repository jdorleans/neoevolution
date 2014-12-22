package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.operator.reproduction.Reproduction;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ReproductionFactory<T extends Reproduction, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
