package org.neoevolution.factory;

import org.neoevolution.core.configuration.Configurable;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ConfigurableFactory<T, C> extends Factory<T>, Configurable<C> {

}
