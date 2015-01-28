package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public enum ErrorFunctionType {

    DE, MSE, RMSE;

    public static boolean isDE(ErrorFunctionType type) {
        return DE.equals(type);
    }

    public static boolean isMSE(ErrorFunctionType type) {
        return MSE.equals(type);
    }

    public static boolean isRMSE(ErrorFunctionType type) {
        return RMSE.equals(type);
    }

}
