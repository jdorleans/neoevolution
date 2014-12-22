package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.operator.speciation.Speciation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 17 2014
 */
public interface SpeciationFactory<T extends Speciation, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
