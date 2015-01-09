package org.neoevolution.factory.operator.reproduction;

import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ReproductionFactory<T extends Reproduction, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
