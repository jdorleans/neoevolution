package org.neoevolution.mvc.model;

public enum NeuronType {

    BIAS, INPUT, HIDDEN, OUTPUT;

    public static boolean isBias(NeuronType type) {
        return BIAS.equals(type);
    }

    public static boolean isInput(NeuronType type) {
        return INPUT.equals(type);
    }

    public static boolean isHidden(NeuronType type) {
        return HIDDEN.equals(type);
    }

    public static boolean isOutput(NeuronType type) {
        return OUTPUT.equals(type);
    }

    public static boolean isInputOrBias(NeuronType type) {
        return isInput(type) || isBias(type);
    }

}
