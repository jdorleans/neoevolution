package org.neoevolution.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    public static <K, V> Map<K, V> createHashMap(int size) {
        return new HashMap<>(getSize(size));
    }

    public static <E> Set<E> createHashSet(int size) {
        return new HashSet<>(getSize(size));
    }

    public static <E> Set<E> createLinkedHashSet(int size) {
        return new LinkedHashSet<>(getSize(size));
    }

    public static <E> Set<E> createLinkedHashSet(int size, boolean useMinimum) {
        return new LinkedHashSet<>(getSize(size, useMinimum));
    }

    public static <K, V> Map<K, V> createConcurrentHashMap(int size) {
        return new ConcurrentHashMap<>(getSize(size));
    }

}
