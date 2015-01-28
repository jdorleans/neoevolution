package org.neoevolution.util;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
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
