package org.neoevolution.util;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 02/11/14.
 */
public class MapUtils {

    public static final double FACTOR = 0.75;

    public static final int MIN_CAPACITY = 16;


    public static int getSize(int size) {
        return getSize(size, true);
    }

    public static int getSize(int size, boolean useMinimum)
    {
        int s = (int) (size / FACTOR) + 1;

        if (useMinimum) {
            s = Math.max(MIN_CAPACITY, s);
        }
        return s;
    }

}
