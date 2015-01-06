package org.neoevolution.factory.model.configuration;

import org.neoevolution.mvc.model.configuration.Configurable;
import org.neoevolution.factory.Factory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ConfigurableFactory<T, C> extends Factory<T>, Configurable<C> {

}
