package org.neoevolution.factory.operator.speciation;

import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 17 2014
 */
public interface SpeciationFactory<T extends Speciation, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
