package org.neoevolution.factory.model.configuration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractConfigurableFactory<T, C> implements ConfigurableFactory<T, C> {

    protected C configuration;


    @Override
    public void configure(C configuration) {
        this.configuration = configuration;
    }


    public C getConfiguration() {
        return configuration;
    }
    public void setConfiguration(C configuration) {
        this.configuration = configuration;
    }

}
