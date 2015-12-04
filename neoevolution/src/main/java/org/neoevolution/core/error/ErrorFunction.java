package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface ErrorFunction<T extends ErrorFunctionType> extends Cloneable {

    T getType();

    void reset();

    void add(double ideal, double actual);

    double calculate();

    ErrorFunction clone() throws CloneNotSupportedException;

}
