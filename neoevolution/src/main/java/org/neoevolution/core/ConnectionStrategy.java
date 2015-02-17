package org.neoevolution.core;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Feb 14 2015
 */
public enum ConnectionStrategy {

    RANDOM_ONE, RANDOM_ALL, INDEX_RELATED, FULLY;

    public static boolean isRandomOne(ConnectionStrategy strategy) {
        return RANDOM_ONE.equals(strategy);
    }

    public static boolean isRandomAll(ConnectionStrategy strategy) {
        return RANDOM_ALL.equals(strategy);
    }

    public static boolean isIndexRelated(ConnectionStrategy strategy) {
        return INDEX_RELATED.equals(strategy);
    }

    public static boolean isFully(ConnectionStrategy strategy) {
        return FULLY.equals(strategy);
    }

}
