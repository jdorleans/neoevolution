package org.neoevolution.core.activation;

public abstract class AbstractActivationFunction implements ActivationFunction {

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
