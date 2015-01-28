package org.neoevolution.factory.model.configuration;

import org.neoevolution.factory.Factory;
import org.neoevolution.mvc.model.configuration.Configurable;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface ConfigurableFactory<T, C> extends Factory<T>, Configurable<C> {

}
