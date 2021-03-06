package org.neoevolution.mvc.model.configuration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface Configurable<T> {

    void configure(T configuration);

}
