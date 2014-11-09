package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 02/11/14.
 */
public enum ErrorFunctionType {

    MSE, RMSE;

    public static boolean isMSE(ErrorFunctionType type) {
        return MSE.equals(type);
    }

    public static boolean isRMSE(ErrorFunctionType type) {
        return RMSE.equals(type);
    }

}
