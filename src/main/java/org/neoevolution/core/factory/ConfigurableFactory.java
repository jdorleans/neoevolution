package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface ConfigurableFactory<T, C extends GAConfiguration> extends Factory<T> {

    void configure(C configuration);

}
