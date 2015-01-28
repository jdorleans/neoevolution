package org.neoevolution.util;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public final class Times {

    private Times() { }

    public static final long MILLIS_MILLISECOND = 1;
    public static final long MILLIS_SECOND = 1000;
    public static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    public static final long MILLIS_HOUR = 60 * MILLIS_MINUTE;
    public static final long MILLIS_DAY = 24 * MILLIS_HOUR;
    public static final long MILLIS_WEEK = 7 * MILLIS_DAY;    
    public static final long MILLIS_MONTH = 4 * MILLIS_WEEK;    
    public static final long MILLIS_YEAR = 365 * MILLIS_DAY;

    public static final long SECOND_SECOND = 1;
    public static final long SECOND_MINUTE = 60 * SECOND_SECOND;
    public static final long SECOND_HOUR = 60 * SECOND_MINUTE;
    public static final long SECOND_DAY = 24 * SECOND_HOUR;
    public static final long SECOND_WEEK = 7 * SECOND_DAY;
    public static final long SECOND_MONTH = 30 * SECOND_DAY;
    public static final long SECOND_YEAR = 365 * SECOND_DAY;

}
