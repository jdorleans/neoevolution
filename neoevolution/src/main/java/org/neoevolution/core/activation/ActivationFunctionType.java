package org.neoevolution.core.activation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public enum ActivationFunctionType {

    BINARY, LINEAR, SIGMOID, TANH;

    public static boolean isBinary(ActivationFunctionType type) {
        return BINARY.equals(type);
    }

    public static boolean isLinear(ActivationFunctionType type) {
        return LINEAR.equals(type);
    }

    public static boolean isSigmoid(ActivationFunctionType type) {
        return SIGMOID.equals(type);
    }

    public static boolean isTanh(ActivationFunctionType type) {
        return TANH.equals(type);
    }

}
