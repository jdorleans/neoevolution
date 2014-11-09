package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
public interface ErrorFunction {

    ErrorFunctionType getType();

    void reset();

    void add(double ideal, double actual);

    double calculate();

}
