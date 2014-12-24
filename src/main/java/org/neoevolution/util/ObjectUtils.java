package org.neoevolution.util;

public final class ObjectUtils {
	
	/**
	 * Utility class
	 */
	private ObjectUtils() { }


    public static <T> T getNotNull(T value, T defaultValue)
    {
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

}
