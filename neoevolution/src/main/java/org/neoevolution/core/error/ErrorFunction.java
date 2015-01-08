package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
public interface ErrorFunction extends Cloneable {

    ErrorFunctionType getType();

    void reset();

    void add(double ideal, double actual);

    double calculate();

    ErrorFunction clone() throws CloneNotSupportedException;

}
