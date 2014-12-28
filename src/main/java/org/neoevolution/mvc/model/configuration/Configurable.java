package org.neoevolution.mvc.model.configuration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 21 2014
 */
public interface Configurable<T> {

    void configure(T configuration);

}