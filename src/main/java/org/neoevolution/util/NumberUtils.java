package org.neoevolution.util;

public final class NumberUtils {

	/**
	 * Utility class
	 */
	private NumberUtils() { }

    protected static <T extends Number> T getNotNullOrDefault(T value, T defaultValue) {
        return ObjectUtils.getNotNull(value, defaultValue);
    }

    public static Byte getNotNull(Byte value) {
        return getNotNull(value, (byte) 0);
    }
    public static Byte getNotNull(Byte value, byte defaultValue) {
        return getNotNullOrDefault(value, defaultValue);
    }

    public static Short getNotNull(Short value) {
        return getNotNull(value, (short) 0);
    }
    public static Short getNotNull(Short value, short defaultValue) {
        return getNotNullOrDefault(value, defaultValue);
    }

    public static Integer getNotNull(Integer value) {
        return getNotNull(value, 0);
	}
    public static Integer getNotNull(Integer value, int defaultValue) {
        return getNotNullOrDefault(value, defaultValue);
    }

    public static Long getNotNull(Long value) {
        return getNotNull(value, 0l);
	}
    public static Long getNotNull(Long value, long defaultValue) {
        return getNotNullOrDefault(value, defaultValue);
    }

    public static Float getNotNull(Float value) {
        return getNotNull(value, 0f);
    }
    public static Float getNotNull(Float value, float defaultValue) {
        return getNotNullOrDefault(value, defaultValue);
    }

    public static Double getNotNull(Double value) {
        return getNotNull(value, 0d);
    }
    public static Double getNotNull(Double value, double defaultValue) {
        return getNotNullOrDefault(value, defaultValue);
    }

}
