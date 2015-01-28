package org.neoevolution.core.activation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public enum ActivationFunctionType {

    BINARY, LINEAR, SIGMOID, TANH;

    public static boolean isBINARY(ActivationFunctionType type) {
        return BINARY.equals(type);
    }

    public static boolean isLINEAR(ActivationFunctionType type) {
        return LINEAR.equals(type);
    }

    public static boolean isSIGMOID(ActivationFunctionType type) {
        return SIGMOID.equals(type);
    }

    public static boolean isTANH(ActivationFunctionType type) {
        return TANH.equals(type);
    }

}
