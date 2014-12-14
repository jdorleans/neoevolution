package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public abstract class AbstractConfigurableFactory<T, C extends GAConfiguration> implements ConfigurableFactory<T, C> {

    protected C configuration;


    @Override
    public void configure(C configuration) {
        this.configuration = configuration;
    }

}