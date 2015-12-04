package org.neoevolution.core.error;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public enum NEErrorFunctionType implements ErrorFunctionType {

    DE, MSE, RMSE;

    public static boolean isDE(NEErrorFunctionType type) {
        return DE.equals(type);
    }

    public static boolean isMSE(NEErrorFunctionType type) {
        return MSE.equals(type);
    }

    public static boolean isRMSE(NEErrorFunctionType type) {
        return RMSE.equals(type);
    }

    @Override
    public String getName() {
        return this.name();
    }

}
