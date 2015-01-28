package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractErrorFunction implements ErrorFunction {

    protected int size;

    protected double error;

    protected boolean computed;


    @Override
    public void reset() {
        size = 0;
        error = 0;
        computed = false;
    }

    @Override
    public double calculate()
    {
        if (!computed && size > 0) {
            error = compute();
            computed = true;
        }
        return error;
    }

    protected abstract double compute();

    @Override
    public ErrorFunction clone() throws CloneNotSupportedException {
        ErrorFunction clone = (ErrorFunction) super.clone();
        clone.reset();
        return clone;
    }

}
