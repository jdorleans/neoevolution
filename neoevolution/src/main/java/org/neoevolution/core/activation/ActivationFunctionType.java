package org.neoevolution.core.activation;

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
