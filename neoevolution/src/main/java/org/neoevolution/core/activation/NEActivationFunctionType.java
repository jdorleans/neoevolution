package org.neoevolution.core.activation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public enum NEActivationFunctionType implements ActivationFunctionType {

    BINARY, LINEAR, SIGMOID, TANH;

    public static boolean isBinary(NEActivationFunctionType type) {
        return BINARY.equals(type);
    }

    public static boolean isLinear(NEActivationFunctionType type) {
        return LINEAR.equals(type);
    }

    public static boolean isSigmoid(NEActivationFunctionType type) {
        return SIGMOID.equals(type);
    }

    public static boolean isTanh(NEActivationFunctionType type) {
        return TANH.equals(type);
    }

    @Override
    public String getName() {
        return this.name();
    }

}
