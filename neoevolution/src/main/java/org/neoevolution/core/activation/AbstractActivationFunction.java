package org.neoevolution.core.activation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractActivationFunction<T extends ActivationFunctionType> implements ActivationFunction<T> {

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        } else if (obj instanceof AbstractActivationFunction) {
            return getType().equals(((AbstractActivationFunction) obj).getType());
        }
        return false;
    }

    @Override
    public String toString() {
        return getType().toString();
    }

}
